package cn.paopao.zuchebackend.entity;


import cn.paopao.zuchebackend.Annotation.CreateTime;
import cn.paopao.zuchebackend.Annotation.UpdateTime;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class Account implements Serializable, UserDetails {


	private Integer accountId;

	private String username;

	private String password;
	/**
	 * 微信的openid
	 */
	private String openid;

	/**
	 * 微信的session_key
	 */
	private String sessionKey;

	/**
	 * 微信昵称
	 */
	private String nickName;

	/**
	 * 性别 0：未知、1：男、2：女
	 */
	private Integer gender;

	/**
	 * 头像url
	 */
	private String avatarUrl;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 删除状态，0：未删除，1：已删除
	 */
	private Integer deleted;

	private List<Role> roles;

	private List<SimpleGrantedAuthority> authorities;

	@CreateTime
	private Date createTime;

	@UpdateTime
	private Date lastUpdateTime;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.roles != null){
			authorities = new ArrayList<>(this.roles.size());
			for (Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
		}

		return authorities;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
