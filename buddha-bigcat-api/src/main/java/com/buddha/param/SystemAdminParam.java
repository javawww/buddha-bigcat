package com.buddha.param;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemAdminParam extends BaseParam{
	
	/**
	 * 推荐人id
	 */
	private Integer pid;
	/**
	 * 用户名
	 */
	private String username;
    /**
     * 密码
     */
	private String password;
    /**
     * 角色id
     */
	private Integer roleId;
	private Date createTime;
    /**
     * 创建人 只能是管理员
     */
	private Integer createUser;
    /**
     * 电话
     */
	private String tel;
    /**
     * 状态 1-正常 2-冻结 3-删除
     */
	private Integer status;

	private Integer loginSource; // 登录来源（1-web后台、2-APP客户端）
	
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色编码
	 */
	private String roleCode;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	private String avatar; // 头像
	
	private String introduction; // 简介
	
	private String mobile; // 电话
	
	/**
	 * 等级
	 */
	private Integer grade;
	/**
	 * 经验值
	 */
	private Integer gradeValue;
	/**
	 * 积分
	 */
	private Integer integral;
	/**
	 * 账户余额
	 */
	private BigDecimal balance;
	/**
	 * 余额密码
	 */
	private String balancePwd;
}
