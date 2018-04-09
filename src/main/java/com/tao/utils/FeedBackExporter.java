package com.tao.utils;

import com.tao.domain.LogFeedback;
import com.tao.service.FeedBackService;
import com.tao.utils.export.CSVExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author TAO
 * @Date 2018/4/8 17:50
 */
@Service
public class FeedBackExporter extends CSVExport<LogFeedback,String>{

    @Autowired
    private FeedBackService feedBackService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    @Override
    public String[] getHeaders() {
        String[] header = {"期数","时间","一等奖数","二等奖数","三等奖数","四等奖数","五等奖数","六等奖数","总数","中奖率"};
        return header;
    }

    @Override
    public List<LogFeedback> getPageContents(String s, int start, int pagesize) {
        return feedBackService.getList();
    }

    @Override
    public long getTotal(String s) {
        return feedBackService.getCount();
    }

    @Override
    public Object getValue(int index, LogFeedback logFeedback) {
        switch (index){
            case 0:
                return logFeedback.getPeriods();
            case 1:
                return sdf.format(logFeedback.getCreateTime());
            case 2:
                return logFeedback.getP1();
            case 3:
                return logFeedback.getP2();
            case 4:
                return logFeedback.getP3();
            case 5:
                return logFeedback.getP4();
            case 6:
                return logFeedback.getP5();
            case 7:
                return logFeedback.getP6();
            case 8:
                return logFeedback.getTotal();
            case 9:
                return decimalFormat.format(logFeedback.getPercent());
        }
        throw new RuntimeException("头部信息和内容不一致");
    }
}
