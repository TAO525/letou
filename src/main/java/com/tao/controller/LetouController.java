package com.tao.controller;

import com.google.common.collect.Lists;
import com.tao.domain.Letou;
import com.tao.domain.LetouVo;
import com.tao.domain.User;
import com.tao.service.LetouService;
import com.tao.service.RedisService;
import com.tao.utils.LetouConstant;
import com.tao.utils.LetouUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author TAO
 * @Date 2018/1/3 20:06
 */
@Controller
public class LetouController {

    @Resource
    private LetouService letouService;

    @Resource
    private RedisService redisService;

    @RequestMapping("/")
    public String def(){
        return "redirect:/index.html";
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
        long totalPeriods = letouService.getTotalPeriods();
        modelAndView.addObject("totalPeriods",totalPeriods);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("blues")
    public Object blues(Integer limit){
        ArrayList<Long> blues = Lists.newArrayList();
        if(limit == null || limit <= 0){
            for (int i = 1; i <= 33; i++) {
                long totalCount4C = letouService.getTotalCount4C(i);
                blues.add(totalCount4C);
            }
        }else {
            for (int i=1; i<=33; i++){
                long totalCount4C = letouService.getTotalCount4CWithLimit(i,limit);
                blues.add(totalCount4C);
            }
        }
        return blues;
    }

    @ResponseBody
    @RequestMapping("reds")
    public Object reds(Integer limit){
        ArrayList<Long> reds = Lists.newArrayList();
        if(limit == null || limit <= 0){
            for (int i = 1; i <= 16; i++) {
                long totalCount4S = letouService.getTotalCount4S(i);
                reds.add(totalCount4S);
            }
        }else {
            for (int i = 1; i <= 16; i++) {
                long totalCount4S = letouService.getCountByNumber4S(i,limit);
                reds.add(totalCount4S);
            }
        }
        return reds;
    }

    @RequestMapping("overview.html")
    public String overViewhtml(Model model){
        List<Letou> news = Lists.newArrayList();
        if(redisService.exists(LetouConstant.NEWS_KEY)){
             news = (List<Letou>) redisService.get(LetouConstant.NEWS_KEY);
        }else {
            news = letouService.getNews(LetouConstant.DEFAULT_NEWS_LIMIT);
            redisService.set(LetouConstant.NEWS_KEY, news, LetouUtil.getSecondsForNew());
        }
        model.addAttribute("news",news);
        return "overview";
    }

    @RequestMapping("luck")
    @ResponseBody
    public LetouVo luck(){
        LetouVo letouVo = new LetouVo();
        List<Integer> luck = letouService.luck();
        int thirdCount = letouService.getThirdCount(luck);
        letouVo.setIsThird(thirdCount);
        letouVo.setNumbers(luck);
        return letouVo;
    }

    @ResponseBody
    @RequestMapping("news")
    public List<Letou> getNews(){
        if(redisService.exists(LetouConstant.NEWS_KEY)){
            return (List<Letou>) redisService.get(LetouConstant.NEWS_KEY);
        }else {
            List<Letou> news = letouService.getNews(LetouConstant.DEFAULT_NEWS_LIMIT);
            redisService.set(LetouConstant.NEWS_KEY, news, LetouUtil.getSecondsForNew());
            return news;
        }
    }

    @RequestMapping("feedback.html")
    public String feedback(){
        return "feedback";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
