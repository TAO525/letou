package com.tao.service;

import com.tao.domain.Whole;
import com.tao.mapper.WholeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author TAO
 * @Date 2018/3/13 13:32
 */
@Service
public class WholeService {

    @Autowired
    private WholeMapper wholeMapper;

    public void insert(Whole whole){
        Integer c1 = whole.getC1();
        Integer c2 = whole.getC1();
        Integer c3 = whole.getC3();
        Integer c4 = whole.getC4();
        Integer c5 = whole.getC5();
        Integer c6 = whole.getC6();
        Integer s1 = whole.getS1();
        int mod = (c1+c2+c3+c4+c5+c6+s1)%10;
        whole.setMod(mod);
        wholeMapper.insert(whole);
    }
}
