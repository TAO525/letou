package com.tao;

import com.tao.domain.Letou;
import com.tao.domain.Whole;
import com.tao.service.LetouService;
import com.tao.service.WholeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tao.utils.WholeUtil.*;

/**
 * 修复历史数据
 * @Author TAO
 * @Date 2018/3/13 15:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FixedNumbers {

    private static final int threads = 16;//根据蓝球切割

    private static final int ends = 17721088;

    private static final List<BlockingQueue<Whole>> queues = new CopyOnWriteArrayList<>();

    private static final ExecutorService pool = Executors.newFixedThreadPool(threads);

    private static final ExecutorService pool2 = Executors.newFixedThreadPool(threads);

    private static final AtomicInteger atomicCount = new AtomicInteger();

    @Autowired
    private WholeService service;

    @Autowired
    private LetouService letouService;

    static {
        for (int i = 0; i < threads; i++) {
            queues.add(new LinkedBlockingDeque<Whole>());
        }
    }

    public void init() throws InterruptedException {
        for (int i = 0; i < threads; i++) {
//            final int flag = i>9?(i-10):i;\
            final int flag = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    BlockingQueue<Whole> wholes = queues.get(flag);
                    try {
                        while (true) {
                            Whole take = wholes.take();
                            service.fixed(take);
                            atomicCount.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

    }

    @Test
   public void go() throws InterruptedException, ExecutionException {
        init();
        int s1;
        Instant now = Instant.now();
        final List<Callable<Integer>> list = new ArrayList<>();
        final List<Letou> news = letouService.getNews(Integer.MAX_VALUE);

        for (s1 = 1; s1 <= 16; s1++) {
            final int flag = s1 - 1;
            System.out.println(flag);
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
                                                getfixed(whole,news);
                                                queues.get(flag).put(whole);
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

        while (!pool.isTerminated()) {
            if (atomicCount.get() >= ends) {
                System.out.println("结束了");
                pool.shutdownNow();
            } else {
                TimeUnit.SECONDS.sleep(10);
            }
        }
        System.out.println(atomicCount.get());
        System.out.println(Duration.between(now, Instant.now()).getSeconds());
    }

    private void getfixed(Whole whole, List<Letou> news) {
        whole.setP1(firstPrize(whole,news));
        whole.setP2(secondPrize(whole,news));
        whole.setP3(thirdPrize(whole,news));
        whole.setP4(forthPrize(whole,news));
        whole.setP5(fifthPrize(whole,news));
        whole.setP6(sixthPrize(whole,news));
    }

    @Test
    public void test_fixedone() throws InterruptedException {
        Whole whole = new Whole(1,2,3,4,5,6,1);
        whole.setP1(1);
        whole.setP2(2);
        whole.setP3(3);
        whole.setP4(4);
        whole.setP5(5);
        whole.setP6(6);
        service.fixed(whole);
    }

}
