package com.tao.service;

import com.tao.domain.Whole;
import com.tao.mapper.WholeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author TAO
 * @Date 2018/3/13 13:32
 */
@Service
public class WholeService {

    @Autowired
    private WholeMapper wholeMapper;

    public void insert(Whole whole){

        Integer s1 = whole.getS1();
        int mod = s1%16;
        whole.setMod(mod);
        wholeMapper.insert(whole);
    }

    public static void main(String[] args) {
        System.out.println(1%16);
    }

    public void increse(Whole wholeAdd){
        Integer s1 = wholeAdd.getS1();
        int mod = s1%16;
        wholeAdd.setMod(mod);
        wholeMapper.increase(wholeAdd);
    }

    public void fixed(Whole wholeAdd){
        Integer s1 = wholeAdd.getS1();
        int mod = s1%16;
        wholeAdd.setMod(mod);
        wholeMapper.fixed(wholeAdd);
    }
}
