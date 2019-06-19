package com.buddha.pojo.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buddha.component.mybatis.PojoModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
 /**
 * 
 * 系统管理员表-数据库实体对象
 *
 * #############################################################################
 *
 * CopyRight (C) 2018 ShenZhen LoveJava Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 小程序开发，企业系统开发，安卓苹果APP开发，服务器部署<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2019-06-01
 * @版权 一群热爱技术的程序猿
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@TableName("system_admin")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemAdmin extends PojoModel<SystemAdmin> {

    private static final long serialVersionUID = 1L;



	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	/**
	 * 推荐人id
	 */
	private Integer pid;
    /**
     * 登录名
     */
	private String name;
    /**
     * 密码
     */
	private String password;
	/**
	 * 等级
	 */
	private Integer grade;
	/**
	 * 经验值
	 */
	@TableField("grade_value")
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
	@TableField("balance_pwd")
	private String balancePwd;
    /**
     * 角色id
     */
	@TableField("role_id")
	private Integer roleId;
	
	/**
	 * 角色名称
	 */
	@TableField("role_name")
	private String roleName;
	/**
	 * 角色编码
	 */
	@TableField("role_code")
	private String roleCode;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	private String avatar; // 头像
	
	private String introduction; // 简介
	
	private String mobile; // 电话
	
    /**
     * 电话
     */
	private String tel;
    /**
     * 状态 1-正常 2-冻结 3-删除
     */
	private Integer status;
	
	  /**
     * 删除
     */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 创建人
     */
	@TableField("create_id")
	private Integer createId;
	
	@TableField("create_time")
	private Date createTime;
	
	@TableField("update_time")
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
