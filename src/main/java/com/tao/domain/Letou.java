package com.tao.domain;

import java.util.Date;

public class Letou{
    //id
    private Long id;

    //期数
    private Long periods;

    //时间
    private Date time;

    private String c1;

    private String c2;

    private String c3;

    private String c4;

    private String c5;

    private String c6;

    private String s1;

    //幸运蓝球
    private String s2;

    //一等奖人数
    private Integer firstNum;

    //一等奖金额
    private Long firstPrice;

    //二等奖人数
    private Integer secondNum;

    //二等奖金额
    private Long secondPrice;

    //三等奖人数
    private Integer thirdNum;

    //三等奖金额
    private Long thirdPrice;

    //是否删除
    private String del;

    //创建时间
    private Date createTime;

    //修改时间
    private Date modifyTime;

    //备注
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPeriods() {
        return periods;
    }

    public void setPeriods(Long periods) {
        this.periods = periods;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public String getC5() {
        return c5;
    }

    public void setC5(String c5) {
        this.c5 = c5;
    }

    public String getC6() {
        return c6;
    }

    public void setC6(String c6) {
        this.c6 = c6;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public Integer getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(Integer firstNum) {
        this.firstNum = firstNum;
    }

    public Long getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Long firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Integer getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(Integer secondNum) {
        this.secondNum = secondNum;
    }

    public Long getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Long secondPrice) {
        this.secondPrice = secondPrice;
    }

    public Integer getThirdNum() {
        return thirdNum;
    }

    public void setThirdNum(Integer thirdNum) {
        this.thirdNum = thirdNum;
    }

    public Long getThirdPrice() {
        return thirdPrice;
    }

    public void setThirdPrice(Long thirdPrice) {
        this.thirdPrice = thirdPrice;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}