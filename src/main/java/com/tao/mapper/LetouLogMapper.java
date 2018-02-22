package com.tao.mapper;

import com.tao.domain.LetouLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LetouLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou_log
     *
     * @mbggenerated Mon Jan 08 13:51:28 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou_log
     *
     * @mbggenerated Mon Jan 08 13:51:28 CST 2018
     */
    int insert(LetouLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou_log
     *
     * @mbggenerated Mon Jan 08 13:51:28 CST 2018
     */
    int insertSelective(LetouLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou_log
     *
     * @mbggenerated Mon Jan 08 13:51:28 CST 2018
     */
    LetouLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou_log
     *
     * @mbggenerated Mon Jan 08 13:51:28 CST 2018
     */
    int updateByPrimaryKeySelective(LetouLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table letou_log
     *
     * @mbggenerated Mon Jan 08 13:51:28 CST 2018
     */
    int updateByPrimaryKey(LetouLog record);

    List<LetouLog> getList(LetouLog LetouLog);

    List<LetouLog> getListGtId(@Param("id")long id);
}