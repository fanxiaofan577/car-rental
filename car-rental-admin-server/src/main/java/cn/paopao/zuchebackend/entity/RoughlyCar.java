package cn.paopao.zuchebackend.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (RoughlyCar)实体类
 *
 * @since 2022-02-10 15:58:41
 */
@Data
public class RoughlyCar implements Serializable {
    private static final long serialVersionUID = -76084189455616774L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 系列名称
     */
    private String name;
    /**
     * 图片url

     */
    private String picUrl;
    /**
     * 价格
     */
    private Double price;
    /**
     * 颜色集合
     */
    private String colorSet;
    /**
     * 分类id
     */
    private Integer cateId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间

     */
    private Date lastUpdateTime;

}

