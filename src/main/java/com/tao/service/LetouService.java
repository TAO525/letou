package com.tao.service;

import com.google.common.collect.Lists;
import com.tao.domain.Letou;
import com.tao.domain.LetouLog;
import com.tao.mapper.LetouLogMapper;
import com.tao.mapper.LetouMapper;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(LetouService.class);

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

    @Resource
    private LetouLogMapper letouLogMapper;

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

    /**
     * 获取总金额
     * @param type
     * @return
     */
    public long getTotal(int type){
        return letouMapper.getTotal(type);
    }

    /**
     * 获取随机数字
     * 规则1 排除上期中奖号码
     * 规则2 排除已经出现过的一等奖号码
     * 规则3 和数在64到144之间 理由
     *          1.SELECT * FROM (SELECT periods,(c1+c2+c3+c4+c5+c6) as he,COUNT(1) as con FROM letou GROUP BY he) tab WHERE con > '10' ORDER BY he ;
     *              历史数据中中奖数字和数出现次数大于10的和数情况
     *          2.SELECT periods,(c1+c2+c3+c4+c5+c6) as he,COUNT(1) as con FROM letou GROUP BY he ORDER BY he  ;
     *              历史数据中和数覆盖范围
     * @return
     */
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
        List<Integer> bluePool = new ArrayList<>(diffblue);
        List<Integer> redPool = new ArrayList<>(diffred);
        List<Integer> blueBalls = Lists.newArrayList();
        int he = 0;
        for (int i =0;i<6; i++)
        {
            int _index=(int)(Math.random()* bluePool.size());
            Integer blueBall=bluePool.get(_index);
            //如何删除一个元素
            bluePool.remove(_index);
            blueBalls.add(blueBall);
            he += blueBall;
        }
        Collections.sort(blueBalls);
        if(he < 64 || he > 144){
            logger.info("不在和数之内{}",blueBalls.toString());
            return luck();
        }
        int _index=(int)(Math.random()* redPool.size());
        Integer redball=redPool.get(_index);
        Letou luck = new Letou();
        luck.setC1(blueBalls.get(0)+"");
        luck.setC2(blueBalls.get(1)+"");
        luck.setC3(blueBalls.get(2)+"");
        luck.setC4(blueBalls.get(3)+"");
        luck.setC5(blueBalls.get(4)+"");
        luck.setC6(blueBalls.get(5)+"");
        List<Letou> list = letouMapper.getList(luck);
        if(list != null && list.size() > 0 ){
            logger.info("出现重复数字{}",blueBalls.toString());
            return luck();
        }
        blueBalls.add(redball);
        logger.info("本次幸运数字{}",blueBalls.toString());
        letouLogMapper.insertSelective(new LetouLog(blueBalls));
        return blueBalls;
    }

    /**
     * 获取篮球出现的次数
     * type 1-6
     * num 1-33
     * @param type
     * @param num
     * @return
     */
    public long getCountByNumber4C(int type,int num,Integer limit){
        return letouMapper.getCountByNumber4C(type, num,limit);
    }

    /**
     * 获取红球出现的次数
     * @param num 1-16
     * @param limit 倒数期数 不加为全部
     * @return
     */
    public long getCountByNumber4S(int num,Integer limit){
        return letouMapper.getCountByNumber4S(num,limit);
    }

    /**
     * 获取某个数字出现的总次数
     * @param num
     * @return
     */
    public long getTotalCount4S(int num){
        return letouMapper.getTotalCount4S(num);
    }

    public long getTotalCount4C(int num){
        return letouMapper.getTotalCount4C(num);
    }
}
