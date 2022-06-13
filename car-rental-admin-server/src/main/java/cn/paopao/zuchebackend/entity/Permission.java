package cn.paopao.zuchebackend.entity;

import cn.paopao.zuchebackend.Annotation.CreateTime;
import cn.paopao.zuchebackend.Annotation.UpdateTime;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class Permission implements Serializable {


	private Long permissionId;


	/**
	 * 资源权限url
	 */
	private String url;

	/**
	 * 请求方式
	 */
	private String method;

	/**
	 * 删除状态，0：未删除，1：已删除
	 */
	private Integer deleted;

	/**
	 * 角色集合
	 */
	private List<Role> roles;

	@CreateTime
	private Date createTime;

	@UpdateTime
	private Date lastUpdateTime;

}
