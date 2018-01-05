package com.tao.service;

import com.google.common.collect.Lists;
import com.tao.domain.Letou;
import com.tao.mapper.LetouMapper;
import org.springframework.stereotype.Service;
import com.google.common.collect.Sets;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author TAO
 * @Date 2018/1/3 20:04
 */
@Service
public class LetouService {

    public static Set<Integer> ALLBLUE = new HashSet<>();
    public static Set<Integer> ALLRED = new HashSet<>();
    static {
        for(int i=1;i<=33;i++){
            ALLBLUE.add(i);
        }
        for(int i=1;i<=16;i++){
            ALLRED.add(i);
        }
    }

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

    public List<Letou> getNews(int limitnum){
        return letouMapper.getNews(limitnum);
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

    public List<Integer> luck(){
        Letou aNew = getNew();
        int c1 = Integer.parseInt(aNew.getC1());
        int c2 = Integer.parseInt(aNew.getC2());
        int c3 = Integer.parseInt(aNew.getC3());
        int c4 = Integer.parseInt(aNew.getC4());
        int c5 = Integer.parseInt(aNew.getC5());
        int c6 = Integer.parseInt(aNew.getC6());
        Set<Integer> excludeBlue = Sets.newHashSet(c1, c2, c3, c4, c5, c6);
        //默认五期
        List<Letou> news = getNews(5);
        Set<Integer> excludeRed = new HashSet<>();
        for (Letou i : news){
            excludeBlue.add(Integer.parseInt(i.getS1()));
        }
        Sets.SetView<Integer> diffblue = Sets.difference(ALLBLUE, excludeBlue);
        Sets.SetView<Integer> diffred = Sets.difference(ALLRED,excludeRed);
//        for(Integer i:diffblue){
//            System.out.print(i);
//        }
//        System.out.println("----");
//        for(Integer i:ALLBLUE){
//            System.out.print(i);
//        }
        List<Integer> bluePool = new ArrayList<>(diffblue);
        List<Integer> redPool = new ArrayList<>(diffred);
        ArrayList<Integer> blueBalls = Lists.newArrayList();
        for (int i =0;i<6; i++)
        {
            int _index=(int)(Math.random()* bluePool.size());
            Integer blueBall=bluePool.get(_index);
            //System.out.print("("+(_index+1)+")"+mNums.get(_index)+"-");
            //如何删除一个元素
            bluePool.remove(_index);
            blueBalls.add(blueBall);
//            System.out.println(blueBall);
        }
        int _index=(int)(Math.random()* redPool.size());
        Integer redball=redPool.get(_index);
        //System.out.print("("+(_index+1)+")"+mNums.get(_index)+"-");
        System.out.println(redball);
        return null;
    }
}
