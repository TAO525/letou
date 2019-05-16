package com.tao.task;

import com.tao.domain.Letou;
import com.tao.domain.Whole;
import com.tao.service.LetouService;
import com.tao.service.RedisService;
import com.tao.service.WholeService;
import com.tao.utils.LetouConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tao.utils.WholeUtil.*;

/**
 * 定时任务 更新全部中奖记录 为了效率 舍弃六等奖统计
 * @Author TAO
 * @Date 2018/2/22 18:22
 */
@Component
public class WholeUpdate {

    Logger logger = LoggerFactory.getLogger(WholeUpdate.class);

    @Autowired
    private LetouService letouService;

    @Autowired
    private WholeService wholeService;

    @Autowired
    private RedisService redisService;

    private static final int threads = 16;//根据蓝球切割

    private static final List<BlockingQueue<Whole>> queues = new CopyOnWriteArrayList<>();

    private static ExecutorService pool;

    private static final ExecutorService pool2 = Executors.newFixedThreadPool(threads);

    private static final AtomicInteger atomicCount = new AtomicInteger();

    private static final Whole POSITION = new Whole();//结束标志

    static {
        for (int i = 0; i < threads; i++) {
            queues.add(new LinkedBlockingDeque<Whole>());
        }
    }

    /*
    //数据状态会被保留 所以在init中需要清空
    @Scheduled(initialDelay = 60 * 1000,fixedDelay = 60*1000)
    public void test() throws InterruptedException {
        atomicCount.set(0);
        queues.get(0).put(new Whole());
        atomicCount.incrementAndGet();
        System.out.println("====="+atomicCount.get());
        System.out.println(queues.get(0).size());
    }*/
    /**
     * 更新中奖信息
     * 每周1 3 5 晚上3:40跑
     */
//    @Scheduled(initialDelay = 20 * 1000,fixedDelay = 24*60*60*1000)
    @Scheduled(cron = "0 40 3 * * MON,WED,FRI")
    public void scan() throws InterruptedException {
        logger.info("更新中奖信息任务开始");
        Letou letou = letouService.getNewNoCache();
        if(redisService.get(LetouConstant.NEW_PERIOD)!=null) {
            int period_flag = (int) redisService.get(LetouConstant.NEW_PERIOD);
            //节假日没有开奖
            if (letou.getPeriods() == period_flag) {
                logger.info("放假没开");
                return;
            }
        }
        init();
        final List<Callable<Integer>> list = new ArrayList<>();
        logger.info("更新中奖信息最新号码"+letou);
        Instant now = Instant.now();
        final List<Letou> news = Arrays.asList(letou);
        for (int s1 = 1; s1 <= 16; s1++) {
            final int flag = s1 - 1;
            Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() {
                    int c1, c2, c3, c4, c5, c6;
                    int count = 0;
                    for (c1 = 1; c1 <= 28; c1++) {
                        for (c2 = c1 + 1; c2 <= 29; c2++) {
                            for (c3 = c2 + 1; c3 <= 30; c3++) {
                                for (c4 = c3 + 1; c4 <= 31; c4++) {
                                    for (c5 = c4 + 1; c5 <= 32; c5++) {
                                        for (c6 = c5 + 1; c6 <= 33; c6++) {
                                            final Whole whole = new Whole(c1, c2, c3, c4, c5, c6, flag + 1);
                                            try {
                                                boolean getfixed = getfixed(whole, news);
                                                if(getfixed) {
//                                                    System.out.println("入列"+(atomicCount2.incrementAndGet()));
                                                    queues.get(flag).put(whole);
                                                }
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return count;
                }
            };
            list.add(callable);

        }
        List<Future<Integer>> futures = pool2.invokeAll(list);

        for(BlockingQueue<Whole> queue:queues){
            queue.put(POSITION);
        }
        pool.shutdown();
//        pool.awaitTermination(300,TimeUnit.MINUTES);
        while (!pool.awaitTermination(15, TimeUnit.MINUTES)) {
            logger.info("线程池还未关闭");
        }
        logger.info("线程池已经关闭");
        //更新最新期数
        redisService.set(LetouConstant.NEW_PERIOD,letou.getPeriods());
        logger.info("更新中奖信息任务结束,更新条数"+atomicCount.get());
        logger.info("更新中奖信息任务花费{}秒", Duration.between(now,Instant.now()).getSeconds());
    }

    public void init() throws InterruptedException {
        atomicCount.set(0);
        pool = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            final int flag = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    BlockingQueue<Whole> wholes = queues.get(flag);
                    try {
                        while (true) {
                            Whole take = wholes.take();
                            if(take == POSITION){
                                break;
                            }
                            wholeService.increse(take);
                            atomicCount.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    private boolean getfixed(Whole whole, List<Letou> news) {
        if(news.isEmpty() || news.size() != 1){
            return false;
        }
       /* if(sixthPrize(whole,news)>0){
            whole.setPrize(6);
            return true;
        }*/
        if(fifthPrize(whole,news)>0){
            whole.setPrize(5);
            return true;
        }
        if(forthPrize(whole,news)>0){
            whole.setPrize(4);
            return true;
        }
        if(thirdPrize(whole,news)>0){
            whole.setPrize(3);
            return true;
        }
        if(secondPrize(whole,news)>0){
            whole.setPrize(2);
            return true;
        }
        if(firstPrize(whole,news) >0){
            whole.setPrize(1);
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Whole> queue = new LinkedBlockingDeque<>();

        for (int i = 0; i < 11; i++) {
            if(i == 2){
                queue.put(POSITION);
            }else{
                queue.put(new Whole());
            }
        }

        while (true){
            Whole take = queue.take();
            if(take == POSITION){
                break;
            }
            System.out.println(take.getC1());
        }
    }

}
