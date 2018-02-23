package com.tao.domain;

import java.util.Date;
import java.util.List;

public class LetouLog {
    //id
    private Long id;

    private String c1;

    private String c2;

    private String c3;

    private String c4;

    private String c5;

    private String c6;

    private String s1;

    //创建时间
    private Date createTime;

    //修改时间
    private Date modifyTime;

    //备注
    private String remark;

    public LetouLog() {
    }

    public LetouLog(List<Integer> blueBalls) {
        if(blueBalls != null && blueBalls.size()==7) {
            this.c1 = String.valueOf(blueBalls.get(0));
            this.c2 = String.valueOf(blueBalls.get(1));
            this.c3 = String.valueOf(blueBalls.get(2));
            this.c4 = String.valueOf(blueBalls.get(3));
            this.c5 = String.valueOf(blueBalls.get(4));
            this.c6 = String.valueOf(blueBalls.get(5));
            this.s1 = String.valueOf(blueBalls.get(6));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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