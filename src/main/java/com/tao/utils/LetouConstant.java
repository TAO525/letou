package com.tao.utils;

/**
 * @Author TAO
 * @Date 2018/1/15 10:30
 */
public final class LetouConstant {
    //最新一期
    public static final String NEW_KEY = "new";

    //总中奖人次
    public static final String TOTAL_PEOPLE_KEY = "total_people";

    //总奖金
    public static final String TOTAL_KEY = "total";

    //总期数
    public static final String TOTAL_PERIODS_KEY = "total_periods";

    //默认展示历史数据条数
    public static final int DEFAULT_NEWS_LIMIT = 10;

    //历史数据
    public static final String NEWS_KEY = "history";

    //redis中上次最新数据的游标
    public static final String LOG_DEX = "log_dex";

    //redis中最新期数 用来防止节假日休息
    public static final String NEW_PERIOD = "new_period_dex";
}
