package com.tao.task;

import com.tao.domain.Letou;
import com.tao.domain.LetouLog;
import com.tao.mapper.LetouLogMapper;
import com.tao.service.LetouService;
import com.tao.service.RedisService;
import com.tao.utils.LetouConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务 统计随机生成的数字有无中3等奖以上奖项
 * @Author TAO
 * @Date 2018/2/22 18:22
 */
@Component
public class Congratulation {

    @Autowired
    private LetouService letouService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private LetouLogMapper letouLogMapper;

    @Scheduled(cron = "0 0/1 * * * *")
    public void test(){
        System.out.println("hello world");
    }

    /**
     * 查看历史数据中中奖信息
     * 每周1 3 5 晚上跑
     */
    // TODO: 2018/2/22
    @Scheduled(cron = "0 0/1 * * * *")
    public void scan(){
        Letou letou = letouService.getNew();
        Object logDex = redisService.get(LetouConstant.LOG_DEX);
        long id = 0;
        if(logDex != null){
            id = Long.valueOf(String.valueOf(logDex));
        }
        List<LetouLog> listGtId = letouLogMapper.getListGtId(id);
        int size = listGtId.size();
        //更新坐标
        if(size > 0){
            id += size;
            redisService.set(LetouConstant.LOG_DEX,id);
            //查看中奖情况 有的话发送邮件

        }

    }
}
