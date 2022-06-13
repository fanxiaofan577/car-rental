package cn.paopao.zuchebackend.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Order)实体类
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = -45969400976948677L;
    /**
     * 主键id,自增
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Long serialNumber;
    /**
     * 车辆id
     */
    private Integer cid;
    /**
     * 账户id
     */
    private Integer aid;
    /**
     * 预约时间(时间戳)
     */
    private Integer bookingTime;
    /**
     * 结束用车时间（时间戳）
     */
    private Integer endTime;
    /**
     * 租借天数
     */
    private Integer rentDays;
    /**
     * 取车人
     */
    private String getPreson;
    /**
     * 取车人身份证号
     */
    private String cardId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 订单状态(0,1,2,3,4)0:预约中;1:确认档期;2:使用中;3:完成订单;4:取消订单；5：订单超时
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 取消原因
     */
    private String cause;
    /**
     * 实际付款
     */
    private Double relPay;
    /**
     * 原价格
     */
    private Double originalPrice;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

    private Car car;

    private Account account;

}

