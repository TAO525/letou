package com.tao.controller;

import com.tao.service.LetouService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author TAO
 * @Date 2018/1/3 20:06
 */
@Controller
public class LetouController {

    @Resource
    private LetouService letouService;

    @ResponseBody
    @RequestMapping("/")
    public Object getById(){
        return letouService.getById();
    }
}
