package com.tao.controller;

import com.tao.service.LetouService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/test")
    public Object getById(){
        return letouService.getById();
    }

    @RequestMapping("/index.html")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("newballs","123456");
        return modelAndView;
    }
}
