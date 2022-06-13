package cn.paopao.zuchebackend.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Category)实体类
 * @since 2022-02-10 15:40:41
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 463295415754734223L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 删除状态，0：未删除，1：已删除
     */
    private Integer deleted;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间

     */
    private Date lastUpdateTime;


}

