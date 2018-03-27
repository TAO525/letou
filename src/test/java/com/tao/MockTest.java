package com.tao;

import com.google.common.collect.Lists;
import com.tao.controller.LetouController;
import com.tao.domain.Letou;
import com.tao.domain.LetouVo;
import com.tao.service.FeedBackService;
import com.tao.service.LetouService;
import com.tao.service.RedisService;
import com.tao.service.WholeService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author TAO
 * @Date 2018/3/27 16:38
 */
@RunWith(JMockit.class)
public class MockTest {

    @Tested
    LetouController letouController;
    @Injectable
    LetouService letouService;
    @Injectable
    RedisService redisService;
    @Injectable
    WholeService wholeService;
    @Injectable
    FeedBackService feedBackService;

    @Test
    public void test(){
        new Expectations(){
            {
                letouService.luck();
                result = Arrays.asList(1,2,3,4,5,6,7);
            }
        };

        LetouVo result = letouController.luck();
        System.out.println(result);

    }

}
