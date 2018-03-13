package com.tao;

import com.tao.domain.Whole;
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
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author TAO
 * @Date 2018/3/13 15:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitNumbers {

    private static final List<BlockingQueue<Whole>> queues = new ArrayList<>();

    private static final ExecutorService pool = Executors.newFixedThreadPool(10);

    private static final AtomicInteger atomicCount = new AtomicInteger();

    private final CountDownLatch close = new CountDownLatch(1);

    @Autowired
    private WholeService service;

    static {
        for (int i = 0; i < 10; i++) {
            queues.add(new LinkedBlockingDeque<Whole>(20));
        }
    }

    public void init() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
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

    @Test
   public void go() throws InterruptedException {
        init();
       int c1,c2,c3,c4,c5,c6;
       int s1;
       int count = 0;
       Instant now = Instant.now();
       for(s1=1;s1<=16;s1++) {
           for (c1 = 1; c1 <= 28; c1++) {
               for (c2 = c1 + 1; c2 <= 29; c2++) {
                   for (c3 = c2 + 1; c3 <= 30; c3++) {
                       for (c4 = c3 + 1; c4 <= 31; c4++) {
                           for (c5 = c4 + 1; c5 <= 32; c5++) {
                               for (c6 = c5 + 1; c6 <= 33; c6++) {
                                   System.out.println(++count);
                                   if(count>101) break;
                                   final Whole whole = new Whole(c1, c2, c3, c4, c5, c6, s1);
                                   int mod = (c1+c2+c3+c4+c5+c6+s1)% 10;
                                   queues.get(mod).put(whole);
                               }
                           }
                       }
                   }
               }
           }
       }
       while (!pool.isTerminated()){
           if(atomicCount.get() >= 100){
               System.out.println("结束了");
               pool.shutdownNow();
           }else {
               TimeUnit.SECONDS.sleep(10);
           }
       }
       System.out.println(count);
       System.out.println(Duration.between(now,Instant.now()).getSeconds());
   }

}
