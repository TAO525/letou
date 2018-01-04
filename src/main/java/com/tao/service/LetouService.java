package com.tao.service;

import com.tao.domain.Letou;
import com.tao.mapper.LetouMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author TAO
 * @Date 2018/1/3 20:04
 */
@Service
public class LetouService {

    @Resource
    private LetouMapper letouMapper;

    public Letou getById(){
        Letou letou = new Letou();
        letou.setId(1L);
        return letouMapper.getList(letou).get(0);
    }

    public Letou getNew(){
        return letouMapper.getNew();
    }

    /**
     * 获奖人数
     * @param type 1 一等奖 2 二等奖 3 三等奖
     * @return
     */
    public long getTotalPeople(int type){
        return letouMapper.getTotalPeople(type);
    }

    public long getTotal(int type){
        return letouMapper.getTotal(type);
    }

}
