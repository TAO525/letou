package com.tao;

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

/**
 * 新增所有组合数据
 * @Author TAO
 * @Date 2018/3/13 15:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitNumbers {

    private static final int threads = 10;//根据蓝球切割

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
            queues.add(new LinkedBlockingDeque<Whole>(30));
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
                            service.insert(take);
                            atomicCount.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

    }

    /**
     * 利用16个管道分别更新16个表
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
   public void go() throws InterruptedException, ExecutionException {
        init();
        int s1;
//       int total =0;
        Instant now = Instant.now();
       final List<Callable<Integer>> list = new ArrayList<>();


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
//                                               System.out.println(++count);
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
        /*  for(Future<Integer> f : futures){
            Integer integer = f.get ();
            total+=integer;
        }*/
        System.out.println(atomicCount.get());
        while (!pool.isTerminated()) {
            if (atomicCount.get() >= ends) {
                System.out.println("结束了");
                pool.shutdownNow();
            } else {
                TimeUnit.SECONDS.sleep(10);
            }
        }
        System.out.println(Duration.between(now, Instant.now()).getSeconds());
    }

    /**
     * 多线程执行
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void go3() throws InterruptedException, ExecutionException {
        int s1;
        int c1, c2, c3, c4, c5, c6;
        int count = 0;
        Instant now = Instant.now();
        for (s1 = 1; s1 <= 16; s1++) {
            for (c1 = 1; c1 <= 28; c1++) {
                for (c2 = c1 + 1; c2 <= 29; c2++) {
                    for (c3 = c2+1; c3 <= 30; c3++) {
                        final Whole whole = new Whole(c1, c2, 1, 1, 1, 1, s1);
                        System.out.println(++count);
                        pool.execute(new Runnable() {
                            @Override
                            public void run() {
                                service.insert(whole);
                            }
                        });
                    }
                }
            }

        }
        while (!pool.isTerminated()){
            if(count >= 64960){
                pool.shutdown();
            }
        }
        System.out.println(count);
        System.out.println(Duration.between(now, Instant.now()).getSeconds());
    }

    /**
     * 单表数据复制
     * @throws InterruptedException
     */
    @Test
    public void go2() throws InterruptedException {
        int c1,c2,c3,c4,c5,c6;
        int s1;
        int count = 0;
        Instant now = Instant.now();
        for(s1=1;s1<=1;s1++) {
            for (c1 = 1; c1 <= 28; c1++) {
                for (c2 = c1 + 1; c2 <= 29; c2++) {
                    for (c3 = c2 + 1; c3 <= 30; c3++) {
                        for (c4 = c3 + 1; c4 <= 31; c4++) {
                            for (c5 = c4 + 1; c5 <= 32; c5++) {
                                for (c6 = c5 + 1; c6 <= 33; c6++) {
                                    System.out.println(++count);
                                    Whole whole = new Whole(c1, c2, c3, c4, c5, c6, s1);
                                    service.insert(whole);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
        System.out.println(Duration.between(now,Instant.now()).getSeconds());
    }
}
