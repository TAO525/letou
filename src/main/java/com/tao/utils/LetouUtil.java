package com.tao.utils;

import java.util.Calendar;
import java.util.Date;

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

    public static void main(String[] args) {
        System.out.println(getSecondsForNew());
    }
}
