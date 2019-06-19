package com.buddha.component.common.dto;


import com.buddha.pojo.base.SystemAdmin;

import lombok.Data;

@Data
public class LoginAdminBean {

	
	/**
	 * 登录token
	 */
	private String token;

	/**
	 * 客户端类型（安卓或者苹果，参考枚举）
	 */
	private Integer appType;

	/**
	 * 登录来源（web后台、app客户端）
	 */
	private Integer loginSource;
	
	/**
	 * 系统管理员信息
	 */
	private SystemAdmin sysAdmin;
	
	/**
	 *  系统角色
	 */
	private String[] roles;
	
	/**
	 * 介绍
	 */
	private String introduction;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 名称
	 */
	private String name;
}
