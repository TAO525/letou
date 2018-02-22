package com.tao.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @Author TAO
 * @Date 2018/2/22 18:22
 */
@Component
public class Congratulation {

    @Scheduled(cron = "0 0/1 * * * *")
    public void test(){
        System.out.println("hello world");
    }
}
