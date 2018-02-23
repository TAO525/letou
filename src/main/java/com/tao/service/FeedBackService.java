package com.tao.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tao.domain.Letou;
import com.tao.domain.LetouLog;
import com.tao.domain.LogFeedback;
import com.tao.mapper.LetouLogMapper;
import com.tao.mapper.LetouMapper;
import com.tao.mapper.LogFeedbackMapper;
import com.tao.utils.LetouConstant;
import com.tao.utils.LetouUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.tao.utils.LetouUtil.parseInt;

/**
 * @Author TAO
 * @Date 2018/1/3 20:04
 */
@Service
public class FeedBackService {

    @Autowired
    private LogFeedbackMapper logFeedbackMapper;

    public void addFeedBack(LogFeedback logFeedback){
        if(logFeedback == null){
            return;
        }
        logFeedbackMapper.insert(logFeedback);
    }
}
