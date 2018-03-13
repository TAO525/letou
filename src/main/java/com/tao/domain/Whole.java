package com.tao.domain;

import java.util.Date;

/**
 * 用于展示某个号码中奖情况
 */
public class Whole {
    //id
    private Long id;

    private Integer c1;

    private Integer c2;

    private Integer c3;

    private Integer c4;

    private Integer c5;

    private Integer c6;

    private Integer s1;

    //增加几等奖
    private Integer prize;

    //创建时间
    private Date createTime;

    //修改时间
    private Date modifyTime;

    //猜中1等奖数
    private int p1;

    //猜中2等奖数
    private int p2;

    //猜中3等奖数
    private int p3;

    //猜中4等奖数
    private int p4;

    //猜中5等奖数
    private int p5;

    //猜中6等奖数
    private int p6;

    //分表flag
    private Integer mod;

    public Whole() {
    }

    public Whole(Integer c1, Integer c2, Integer c3, Integer c4, Integer c5, Integer c6, Integer s1) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
        this.s1 = s1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getC1() {
        return c1;
    }

    public void setC1(Integer c1) {
        this.c1 = c1;
    }

    public Integer getC2() {
        return c2;
    }

    public void setC2(Integer c2) {
        this.c2 = c2;
    }

    public Integer getC3() {
        return c3;
    }

    public void setC3(Integer c3) {
        this.c3 = c3;
    }

    public Integer getC4() {
        return c4;
    }

    public void setC4(Integer c4) {
        this.c4 = c4;
    }

    public Integer getC5() {
        return c5;
    }

    public void setC5(Integer c5) {
        this.c5 = c5;
    }

    public Integer getC6() {
        return c6;
    }

    public void setC6(Integer c6) {
        this.c6 = c6;
    }

    public Integer getS1() {
        return s1;
    }

    public void setS1(Integer s1) {
        this.s1 = s1;
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

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public int getP3() {
        return p3;
    }

    public void setP3(int p3) {
        this.p3 = p3;
    }

    public int getP4() {
        return p4;
    }

    public void setP4(int p4) {
        this.p4 = p4;
    }

    public int getP5() {
        return p5;
    }

    public void setP5(int p5) {
        this.p5 = p5;
    }

    public int getP6() {
        return p6;
    }

    public void setP6(int p6) {
        this.p6 = p6;
    }

    public Integer getMod() {
        return mod;
    }

    public void setMod(Integer mod) {
        this.mod = mod;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }
}