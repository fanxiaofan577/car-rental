package cn.paopao.zuchebackend.entity;


import cn.paopao.zuchebackend.Annotation.CreateTime;
import cn.paopao.zuchebackend.Annotation.UpdateTime;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class Role implements Serializable {

	private Integer roleId;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 删除状态，0：未删除，1：已删除
	 */
	private Integer deleted;

	@CreateTime
	private Date createTime;

	@UpdateTime
	private Date lastUpdateTime;

}
