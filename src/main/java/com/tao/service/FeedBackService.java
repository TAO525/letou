package com.tao.service;

import com.tao.domain.LogFeedback;
import com.tao.mapper.LogFeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<LogFeedback> getList(){
       return logFeedbackMapper.getListDesc();
    }

    public int getCount(){return logFeedbackMapper.getCount();}
}
