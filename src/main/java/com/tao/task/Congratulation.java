package com.tao.task;

import com.tao.domain.Letou;
import com.tao.domain.LetouLog;
import com.tao.domain.LogFeedback;
import com.tao.service.FeedBackService;
import com.tao.service.LetouService;
import com.tao.service.RedisService;
import com.tao.utils.LetouConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.tao.utils.LetouUtil.*;

/**
 * 定时任务 统计随机生成的数字有无中3等奖以上奖项
 * @Author TAO
 * @Date 2018/2/22 18:22
 */
@Component
public class Congratulation {

    Logger logger = LoggerFactory.getLogger(Congratulation.class);

    @Autowired
    private LetouService letouService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private FeedBackService feedBackService;

  /*  @Scheduled(cron = "0 0/1 * * * MON,WED,SAT")
    public void test(){
        System.out.println("hello world");
    }*/

    /**
     * 查看历史数据中中奖信息
     * 每周1 3 5 晚上跑
     */
//    @Scheduled(initialDelay = 60 * 1000,fixedDelay = 60*60*1000)
    @Scheduled(cron = "0 0 21 * * MON,WED,FRI")
    public void scan(){
        logger.info("反馈定时任务开始");
        Letou letou = letouService.getNew();
        Object logDex = redisService.get(LetouConstant.LOG_DEX);
        long id = 0;
        if(logDex != null){
            id = Long.valueOf(String.valueOf(logDex));
        }
        List<LetouLog> listGtId = letouService.getLogListGtId(id);
        int size = listGtId.size();
        if(size > 0){
            id += size;
            //更新坐标
            redisService.set(LetouConstant.LOG_DEX,id);
            //查看中奖情况 有的话发送邮件
            int firstCount = firstPrize(letou,listGtId);
            int secondCount = secondPrize(letou,listGtId);
            int thirdCount = thirdPrize(letou,listGtId);
            int forthCount = forthPrize(letou, listGtId);
            int fifthCount = fifthPrize(letou, listGtId);
            int sixthCount = sixthPrize(letou, listGtId);
            logger.info(firstCount+"__"+secondCount+"__"+thirdCount
                    +"----"+forthCount+"__"+fifthCount+"__"+sixthCount);
            //记录入库
            LogFeedback logFeedback = new LogFeedback(firstCount,secondCount,thirdCount,forthCount,fifthCount,sixthCount);
            logFeedback.setPeriods(letou.getPeriods());
            logFeedback.setTime(letou.getTime());
            logFeedback.setTotal(size);
            int count = fifthCount+secondCount+thirdCount+forthCount+fifthCount+ sixthCount;
//            System.out.println(count +"==========="+size);
            BigDecimal persent = new BigDecimal((float) count / size).setScale(4,BigDecimal.ROUND_HALF_UP);
            logFeedback.setPercent(persent);
            logFeedback.setDel("n");
            logFeedback.setCreateTime(new Date());
            feedBackService.addFeedBack(logFeedback);
        }
        logger.info("反馈定时任务结束");
    }

}
