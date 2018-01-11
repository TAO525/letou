package com.tao.utils;

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
}
