package com.buddha.pojo.base;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buddha.component.mybatis.PojoModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
 /**
 * 
 * 角色表-数据库实体对象
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
@TableName("system_roles")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemRoles extends PojoModel<SystemRoles> {

    private static final long serialVersionUID = 1L;



	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 角色名称
     */
	@TableField("roles_name")
	private String rolesName;
    /**
     * 角色描述
     */
	private String content;
    /**
     * 创建人
     */
	@TableField("user_id")
	private Integer userId;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	@TableField("create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 状态
     */
	private Integer status;
	/**
	 * 删除
	 */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 角色code,唯一
     */
	@TableField("role_code")
	private String roleCode;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
