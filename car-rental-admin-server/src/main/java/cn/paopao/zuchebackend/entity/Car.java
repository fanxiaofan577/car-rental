package cn.paopao.zuchebackend.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Car)实体类
 *
 * @author fanxiaofan
 * @since 2022-02-08 18:53:54
 */
public class Car implements Serializable {
    private static final long serialVersionUID = -81726782038435247L;
    /**
     * 主键id,自增
     */
    private Integer id;
    /**
     * 车名
     */
    private String name;
    /**
     * 图片url合集
     */
    private String picUrls;
    /**
     * 价格
     */
    private Double price;
    /**
     * vip价格
     */
    private Double vipPrice;
    /**
     * 车牌
     */
    private String licensePlate;
    /**
     * 上牌时间
     */
    private String plateYear;
    /**
     * 车辆配置信息json格式
     */
    private String configuration;
    /**
     * 系列id
     */
    private Integer rcId;
    /**
     * 三日价格
     */
    private Double threeDayPrice;
    /**
     * vip三日价格
     */
    private Double vipThreeDayPrice;
    /**
     * 七日价格
     */
    private Double weekPrice;
    /**
     * vip七日价格
     */
    private Double vipWeekPrice;
    /**
     * 十五日价格
     */
    private Double halfmoonPrice;
    /**
     * vip十五日价格
     */
    private Double vipHalfmoonPrice;
    /**
     * 30日价格
     */
    private Double moonPrice;
    /**
     * vip30日价格
     */
    private Double vipMoonPrice;
    /**
     * 车辆状态(0、1、2);0:在库;1:租界中;2:已经预约
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建时间
     */
    private Date lastUpdateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getPlateYear() {
        return plateYear;
    }

    public void setPlateYear(String plateYear) {
        this.plateYear = plateYear;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public Integer getRcId() {
        return rcId;
    }

    public void setRcId(Integer rcId) {
        this.rcId = rcId;
    }

    public Double getThreeDayPrice() {
        return threeDayPrice;
    }

    public void setThreeDayPrice(Double threeDayPrice) {
        this.threeDayPrice = threeDayPrice;
    }

    public Double getVipThreeDayPrice() {
        return vipThreeDayPrice;
    }

    public void setVipThreeDayPrice(Double vipThreeDayPrice) {
        this.vipThreeDayPrice = vipThreeDayPrice;
    }

    public Double getWeekPrice() {
        return weekPrice;
    }

    public void setWeekPrice(Double weekPrice) {
        this.weekPrice = weekPrice;
    }

    public Double getVipWeekPrice() {
        return vipWeekPrice;
    }

    public void setVipWeekPrice(Double vipWeekPrice) {
        this.vipWeekPrice = vipWeekPrice;
    }

    public Double getHalfmoonPrice() {
        return halfmoonPrice;
    }

    public void setHalfmoonPrice(Double halfmoonPrice) {
        this.halfmoonPrice = halfmoonPrice;
    }

    public Double getVipHalfmoonPrice() {
        return vipHalfmoonPrice;
    }

    public void setVipHalfmoonPrice(Double vipHalfmoonPrice) {
        this.vipHalfmoonPrice = vipHalfmoonPrice;
    }

    public Double getMoonPrice() {
        return moonPrice;
    }

    public void setMoonPrice(Double moonPrice) {
        this.moonPrice = moonPrice;
    }

    public Double getVipMoonPrice() {
        return vipMoonPrice;
    }

    public void setVipMoonPrice(Double vipMoonPrice) {
        this.vipMoonPrice = vipMoonPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}

