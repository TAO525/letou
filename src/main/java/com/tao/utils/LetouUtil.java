package com.tao.utils;

import com.tao.domain.Letou;
import com.tao.domain.LetouLog;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author TAO
 * @Date 2018/1/11 18:05
 */
public class LetouUtil {
    private LetouUtil(){
    }

    public static String parseInt(Integer i){
        if(i < 10){
            return "0"+i;
        }else {
            return String.valueOf(i);
        }
    }

    /**
     * 计算当前时间到明天8点的秒数
     * @return
     */
    public static Long getSecondsForNew(){
        // 获取前月的第一天
        Date now = new Date();
        Calendar cale = Calendar.getInstance();
        cale.setTime(now);
        cale.add(Calendar.DAY_OF_YEAR, 1);
        cale.set(Calendar.HOUR_OF_DAY, 8);
        cale.set(Calendar.MINUTE, 20);
        cale.set(Calendar.SECOND, 0);
        return (cale.getTime().getTime() - now.getTime())/1000;
    }

    /**
     * 查看记录中一等奖数量
     * @param theNew
     * @param logs
     * @return
     */
    public static int firstPrize(Letou theNew, List<LetouLog> logs){
        int count = 0;
        int c1 = Integer.parseInt(theNew.getC1());
        int c2 = Integer.parseInt(theNew.getC2());
        int c3 = Integer.parseInt(theNew.getC3());
        int c4 = Integer.parseInt(theNew.getC4());
        int c5 = Integer.parseInt(theNew.getC5());
        int c6 = Integer.parseInt(theNew.getC6());
        int s1 = Integer.parseInt(theNew.getS1());
        for(LetouLog log : logs){
            int l1 = Integer.parseInt(log.getC1());
            int l2 = Integer.parseInt(log.getC2());
            int l3 = Integer.parseInt(log.getC3());
            int l4 = Integer.parseInt(log.getC4());
            int l5 = Integer.parseInt(log.getC5());
            int l6 = Integer.parseInt(log.getC6());
            int ls1 = Integer.parseInt(log.getS1());
            if(c1 == l1 && c2 == l2 && c3 == l3 && c4 == l4 && c5 == l5 && c6 == l6 && s1 == ls1){
                count ++;
            }
        }
        return count;
    }

    /**
     * 查看二等奖数量 6红
     * @param theNew
     * @param logs
     * @return
     */
    public static int secondPrize(Letou theNew, List<LetouLog> logs){
        int count = 0;
        int c1 = Integer.parseInt(theNew.getC1());
        int c2 = Integer.parseInt(theNew.getC2());
        int c3 = Integer.parseInt(theNew.getC3());
        int c4 = Integer.parseInt(theNew.getC4());
        int c5 = Integer.parseInt(theNew.getC5());
        int c6 = Integer.parseInt(theNew.getC6());
        int s1 = Integer.parseInt(theNew.getS1());
        for(LetouLog log : logs){
            int l1 = Integer.parseInt(log.getC1());
            int l2 = Integer.parseInt(log.getC2());
            int l3 = Integer.parseInt(log.getC3());
            int l4 = Integer.parseInt(log.getC4());
            int l5 = Integer.parseInt(log.getC5());
            int l6 = Integer.parseInt(log.getC6());
            int ls1 = Integer.parseInt(log.getS1());
            if(s1 == ls1){
                continue;
            }
            if(c1 == l1 && c2 == l2 && c3 == l3 && c4 == l4 && c5 == l5 && c6 == l6){
                count ++;
            }
        }
        return count;
    }

    /**
     * 三等奖数量 5红1蓝
     * @param theNew
     * @param logs
     * @return
     */
    public static int thirdPrize(Letou theNew, List<LetouLog> logs){
        int count = 0;
        int c1 = Integer.parseInt(theNew.getC1());
        int c2 = Integer.parseInt(theNew.getC2());
        int c3 = Integer.parseInt(theNew.getC3());
        int c4 = Integer.parseInt(theNew.getC4());
        int c5 = Integer.parseInt(theNew.getC5());
        int c6 = Integer.parseInt(theNew.getC6());
        int s1 = Integer.parseInt(theNew.getS1());
        List<Integer> newList = Arrays.asList(c1,c2,c3,c4,c5,c6);
        for(LetouLog log : logs){
            int right = 0;
            int l1 = Integer.parseInt(log.getC1());
            int l2 = Integer.parseInt(log.getC2());
            int l3 = Integer.parseInt(log.getC3());
            int l4 = Integer.parseInt(log.getC4());
            int l5 = Integer.parseInt(log.getC5());
            int l6 = Integer.parseInt(log.getC6());
            int ls1 = Integer.parseInt(log.getS1());
            if(s1 != ls1){
                continue;
            }
            if(newList.contains(l1)){
                right ++;
            }
            if(newList.contains(l2)){
                right ++;
            }
            if(newList.contains(l3)){
                right ++;
            }
            if(newList.contains(l4)){
                right ++;
            }
            if(newList.contains(l5)){
                right ++;
            }
            if(newList.contains(l6)){
                right ++;
            }
            if(right == 5){
                count ++;
            }
        }
        return count;
    }

    /**
     * 4等奖 4红一蓝或五红
     * @param theNew
     * @param logs
     * @return
     */
    public static int forthPrize(Letou theNew, List<LetouLog> logs){
        int count = 0;
        int c1 = Integer.parseInt(theNew.getC1());
        int c2 = Integer.parseInt(theNew.getC2());
        int c3 = Integer.parseInt(theNew.getC3());
        int c4 = Integer.parseInt(theNew.getC4());
        int c5 = Integer.parseInt(theNew.getC5());
        int c6 = Integer.parseInt(theNew.getC6());
        int s1 = Integer.parseInt(theNew.getS1());
        List<Integer> newList = Arrays.asList(c1,c2,c3,c4,c5,c6);
        for(LetouLog log : logs){
            int right = 0;
            int l1 = Integer.parseInt(log.getC1());
            int l2 = Integer.parseInt(log.getC2());
            int l3 = Integer.parseInt(log.getC3());
            int l4 = Integer.parseInt(log.getC4());
            int l5 = Integer.parseInt(log.getC5());
            int l6 = Integer.parseInt(log.getC6());
            int ls1 = Integer.parseInt(log.getS1());

            if(newList.contains(l1)){
                right ++;
            }
            if(newList.contains(l2)){
                right ++;
            }
            if(newList.contains(l3)){
                right ++;
            }
            if(newList.contains(l4)){
                right ++;
            }
            if(newList.contains(l5)){
                right ++;
            }
            if(newList.contains(l6)){
                right ++;
            }
            //五红
            if(s1 != ls1 && right == 5){
                count ++;
            }
            //四红一蓝
            if(s1 == ls1 && right ==4){
                count ++;
            }
        }
        return count;
    }

    /**
     * 五等奖数量 四红或三红一蓝
     * @param theNew
     * @param logs
     * @return
     */
    public static int fifthPrize(Letou theNew, List<LetouLog> logs){
        int count = 0;
        int c1 = Integer.parseInt(theNew.getC1());
        int c2 = Integer.parseInt(theNew.getC2());
        int c3 = Integer.parseInt(theNew.getC3());
        int c4 = Integer.parseInt(theNew.getC4());
        int c5 = Integer.parseInt(theNew.getC5());
        int c6 = Integer.parseInt(theNew.getC6());
        int s1 = Integer.parseInt(theNew.getS1());
        List<Integer> newList = Arrays.asList(c1,c2,c3,c4,c5,c6);
        for(LetouLog log : logs){
            int right = 0;
            int l1 = Integer.parseInt(log.getC1());
            int l2 = Integer.parseInt(log.getC2());
            int l3 = Integer.parseInt(log.getC3());
            int l4 = Integer.parseInt(log.getC4());
            int l5 = Integer.parseInt(log.getC5());
            int l6 = Integer.parseInt(log.getC6());
            int ls1 = Integer.parseInt(log.getS1());

            if(newList.contains(l1)){
                right ++;
            }
            if(newList.contains(l2)){
                right ++;
            }
            if(newList.contains(l3)){
                right ++;
            }
            if(newList.contains(l4)){
                right ++;
            }
            if(newList.contains(l5)){
                right ++;
            }
            if(newList.contains(l6)){
                right ++;
            }
            //四红
            if(s1 != ls1 && right == 4){
                count ++;
            }
            //三红一蓝
            if(s1 == ls1 && right ==3){
                count ++;
            }
        }
        return count;
    }

    /**
     * 六等奖数量 2红1蓝、1红1蓝、1蓝
     * @param theNew
     * @param logs
     * @return
     */
    public static int sixthPrize(Letou theNew, List<LetouLog> logs){
        int count = 0;
        int c1 = Integer.parseInt(theNew.getC1());
        int c2 = Integer.parseInt(theNew.getC2());
        int c3 = Integer.parseInt(theNew.getC3());
        int c4 = Integer.parseInt(theNew.getC4());
        int c5 = Integer.parseInt(theNew.getC5());
        int c6 = Integer.parseInt(theNew.getC6());
        int s1 = Integer.parseInt(theNew.getS1());
        List<Integer> newList = Arrays.asList(c1,c2,c3,c4,c5,c6);
        for(LetouLog log : logs){
            int right = 0;
            int l1 = Integer.parseInt(log.getC1());
            int l2 = Integer.parseInt(log.getC2());
            int l3 = Integer.parseInt(log.getC3());
            int l4 = Integer.parseInt(log.getC4());
            int l5 = Integer.parseInt(log.getC5());
            int l6 = Integer.parseInt(log.getC6());
            int ls1 = Integer.parseInt(log.getS1());
            if(s1 != ls1){
                continue;
            }
            if(newList.contains(l1)){
                right ++;
            }
            if(newList.contains(l2)){
                right ++;
            }
            if(newList.contains(l3)){
                right ++;
            }
            if(newList.contains(l4)){
                right ++;
            }
            if(newList.contains(l5)){
                right ++;
            }
            if(newList.contains(l6)){
                right ++;
            }
            //两红一蓝
            //一红一蓝
            //一蓝
            if(right <= 2){
                count ++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(getSecondsForNew());
    }
}
