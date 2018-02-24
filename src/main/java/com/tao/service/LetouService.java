package com.tao.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tao.domain.Letou;
import com.tao.domain.LetouLog;
import com.tao.mapper.LetouLogMapper;
import com.tao.mapper.LetouMapper;
import com.tao.utils.LetouConstant;
import com.tao.utils.LetouUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.tao.utils.LetouUtil.parseInt;

/**
 * @Author TAO
 * @Date 2018/1/3 20:04
 */
@Service
public class LetouService {

    @Resource
    private RedisService redisService;

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

    public Letou getById(long id){
        return letouMapper.selectByPrimaryKey(id);
    }

    public Letou getNew(){
        boolean exists = redisService.exists(LetouConstant.NEW_KEY);
        if(exists){
            return (Letou)redisService.get(LetouConstant.NEW_KEY);
        }else {
            Letou aNew = letouMapper.getNew();
            redisService.set(LetouConstant.NEW_KEY,aNew, LetouUtil.getSecondsForNew());//到明天8点20分
            return aNew;
        }
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
        String key = LetouConstant.TOTAL_PEOPLE_KEY+"_"+String.valueOf(type);
        boolean exists = redisService.exists(key);
        if(exists){
            return  Long.valueOf(String.valueOf(redisService.get(key)));
        }else {
            long totalPeople = letouMapper.getTotalPeople(type);
            redisService.set(key,totalPeople, LetouUtil.getSecondsForNew());//到明天8点20分
            return totalPeople;
        }
    }

    /**
     * 获取总金额
     * @param type
     * @return
     */
    public long getTotal(int type){
        String key = LetouConstant.TOTAL_KEY+"_"+String.valueOf(type);
        boolean exists = redisService.exists(key);
        if(exists){
            return Long.valueOf(String.valueOf(redisService.get(key)));
        }else {
            long total = letouMapper.getTotal(type);
            redisService.set(key,total, LetouUtil.getSecondsForNew());//到明天8点20分
            return total;
        }
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
        //默认三期
        List<Letou> news = getNews(3);
        Set<Integer> excludeRed = new HashSet<>();
        for (Letou i : news){
            excludeRed.add(Integer.parseInt(i.getS1()));
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
        luck.setC1(parseInt(blueBalls.get(0)));
        luck.setC2(parseInt(blueBalls.get(1)));
        luck.setC3(parseInt(blueBalls.get(2)));
        luck.setC4(parseInt(blueBalls.get(3)));
        luck.setC5(parseInt(blueBalls.get(4)));
        luck.setC6(parseInt(blueBalls.get(5)));
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
     * 根据位置和期数获取篮球出现的次数
     * type 出现位置1-6
     * num 1-33
     * @param type
     * @param num
     * @return
     */
    public long getCountByNumber4C(int type,int num,Integer limit){
        return letouMapper.getCountByNumber4C(type, num,limit);
    }

    /**
     * 根据位置和期数获取红球出现的次数
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

    public long getTotalCount4CWithLimit(int num,int limit){
        return letouMapper.getTotalCount4CWithLimit(num,limit);
    }

    /**
     * 选择号码中三等奖次数
     */
    public int getThirdCount(List<Integer> nums){
        if(nums == null || nums.size() != 7){
            return 0;
        }
        int thirdcount = 0;
        Integer red = nums.get(6);
        List<Integer> opers = nums.subList(0, 6);
        List<Letou> letous = letouMapper.getByS(red);
        for (Letou l : letous){
            int fit = 0;
            for(int choose : opers){
                int c1 = Integer.parseInt(l.getC1());
                int c2 = Integer.parseInt(l.getC2());
                int c3 = Integer.parseInt(l.getC3());
                int c4 = Integer.parseInt(l.getC4());
                int c5 = Integer.parseInt(l.getC5());
                int c6 = Integer.parseInt(l.getC6());
                if(c1 == choose || c2 == choose || c3 == choose || c4 == choose || c5 == choose || c6==choose){
                    fit ++ ;
                }
            }
            if (fit == 5){
                thirdcount ++;
            }
        }
        return thirdcount;
    }

    /**
     * 所选组合中过一奖次数
     * @return
     */
    public int getOneCount(List<Integer> nums){
        Letou luck = new Letou();
        luck.setC1(parseInt(nums.get(0)));
        luck.setC2(parseInt(nums.get(1)));
        luck.setC3(parseInt(nums.get(2)));
        luck.setC4(parseInt(nums.get(3)));
        luck.setC5(parseInt(nums.get(4)));
        luck.setC6(parseInt(nums.get(5)));
        luck.setS1(parseInt(nums.get(6)));
        List<Letou> list = letouMapper.getList(luck);
        if(list == null){
            return 0;
        }
        return list.size();
    }


    /**
     * 所选组合中过二奖次数
     * @return
     */
    public int getTwoCount(List<Integer> nums){
        Letou luck = new Letou();
        luck.setC1(parseInt(nums.get(0)));
        luck.setC2(parseInt(nums.get(1)));
        luck.setC3(parseInt(nums.get(2)));
        luck.setC4(parseInt(nums.get(3)));
        luck.setC5(parseInt(nums.get(4)));
        luck.setC6(parseInt(nums.get(5)));
        List<Letou> list = letouMapper.getList(luck);
        if(list == null){
            return 0;
        }
        Iterator<Letou> iterator = list.iterator();
        while (iterator.hasNext()) {
            Letou letou = iterator.next();
            if (Integer.valueOf(letou.getS1()).equals(nums.get(6))) {
                iterator.remove();
            }
        }
        return list.size();
    }

    /**
     * 获得总期数
     * @return
     */
    public long getTotalPeriods(){
        String key = LetouConstant.TOTAL_PERIODS_KEY;
        boolean exists = redisService.exists(key);
        if(exists){
            return Long.valueOf(String.valueOf(redisService.get(key)));
        }else {
            long total = letouMapper.getTotalPeriods();
            redisService.set(key,total, LetouUtil.getSecondsForNew());//到明天8点20分
            return total;
        }
    }

    /**
     * 获得大于id数的日志
     */
    public List<LetouLog> getLogListGtId(long id){
        return letouLogMapper.getListGtId(id);
    }
}
