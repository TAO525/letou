package com.tao.domain;

import java.math.BigDecimal;
import java.util.Date;

public class LogFeedback{
    //id
    private Long id;

    //期数
    private Long periods;

    //时间
    private Date time;

    //猜中1等奖数
    private Integer p1;

    //猜中2等奖数
    private Integer p2;

    //猜中3等奖数
    private Integer p3;

    //猜中4等奖数
    private Integer p4;

    //猜中5等奖数
    private Integer p5;

    //猜中6等奖数
    private Integer p6;

    //总尝试次数
    private Integer total;

    //中奖率
    private BigDecimal percent;

    //是否删除
    private String del;

    //创建时间
    private Date createTime;

    //修改时间
    private Date modifyTime;

    //备注
    private String remark;

    public LogFeedback() {
    }

    public LogFeedback(Integer p1, Integer p2, Integer p3, Integer p4, Integer p5, Integer p6) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
    }

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

    public Integer getP1() {
        return p1;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    public Integer getP3() {
        return p3;
    }

    public void setP3(Integer p3) {
        this.p3 = p3;
    }

    public Integer getP4() {
        return p4;
    }

    public void setP4(Integer p4) {
        this.p4 = p4;
    }

    public Integer getP5() {
        return p5;
    }

    public void setP5(Integer p5) {
        this.p5 = p5;
    }

    public Integer getP6() {
        return p6;
    }

    public void setP6(Integer p6) {
        this.p6 = p6;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
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