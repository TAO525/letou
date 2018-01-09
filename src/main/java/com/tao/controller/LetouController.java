package com.tao.controller;

import com.google.common.collect.Lists;
import com.tao.domain.Letou;
import com.tao.service.LetouService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
        DecimalFormat fmt   =   new   DecimalFormat("###,###");
        ModelAndView modelAndView = new ModelAndView("index");
        Letou aNew = letouService.getNew();
        ArrayList<String> blues = Lists.newArrayList(aNew.getC1(), aNew.getC2(), aNew.getC3(), aNew.getC4(), aNew.getC5(), aNew.getC6());
        modelAndView.addObject("blues",blues);
        modelAndView.addObject("red",aNew.getS1());
        modelAndView.addObject("periods","第"+aNew.getPeriods()+"期");
        long totalPeople = letouService.getTotalPeople(1);
        long total = letouService.getTotal(1);
        modelAndView.addObject("totalPeople",fmt.format(totalPeople));
        modelAndView.addObject("total",fmt.format(total));
        return modelAndView;
    }
}
