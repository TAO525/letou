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
}
