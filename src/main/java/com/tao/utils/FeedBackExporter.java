package com.tao.utils;

import com.tao.domain.LogFeedback;
import com.tao.utils.export.CSVExport;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author TAO
 * @Date 2018/4/8 17:50
 */
@Service
public class FeedBackExporter extends CSVExport<LogFeedback,String>{

    @Override
    public String[] getHeaders() {
        return new String[0];
    }

    @Override
    public List<LogFeedback> getPageContents(String s, int start, int pagesize) {
        return null;
    }

    @Override
    public long getTotal(String s) {
        return 0;
    }

    @Override
    public Object getValue(int index, LogFeedback logFeedback) {
        return null;
    }
}
