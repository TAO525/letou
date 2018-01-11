package com.tao.mapper;


import com.tao.domain.Letou;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LetouMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou
     *
     * @mbggenerated Wed Jan 03 18:15:02 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou
     *
     * @mbggenerated Wed Jan 03 18:15:02 CST 2018
     */
    int insert(Letou record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou
     *
     * @mbggenerated Wed Jan 03 18:15:02 CST 2018
     */
    int insertSelective(Letou record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou
     *
     * @mbggenerated Wed Jan 03 18:15:02 CST 2018
     */
    Letou selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou
     *
     * @mbggenerated Wed Jan 03 18:15:02 CST 2018
     */
    int updateByPrimaryKeySelective(Letou record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou
     *
     * @mbggenerated Wed Jan 03 18:15:02 CST 2018
     */
    int updateByPrimaryKey(Letou record);

    List<Letou> getList(Letou Letou);

    Letou getNew();

    List<Letou> getNews(int limitnum);

    long getTotalPeople(@Param("type")int type);

    long getTotal(@Param("type")int type);

    long getCountByNumber4C(@Param("type") int type,@Param("num") int num,@Param("limit")Integer limit);

    long getCountByNumber4S(@Param("num") int num,@Param("limit")Integer limit);

    long getTotalCount4S(int num);

    long getTotalCount4C(int num);

    long getTotalCount4CWithLimit(@Param("num") int num,@Param("limit")Integer limit);
}