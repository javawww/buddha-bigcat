package com.buddha.pojo.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buddha.component.mybatis.PojoModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
 /**
 * 
 * 资源表-数据库实体对象
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
@TableName("system_resources")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemResources extends PojoModel<SystemResources> {

    private static final long serialVersionUID = 1L;



	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer pid;
	private String url;
	private String content;
    /**
     * 1、菜单；2、按钮
     */
	private Integer type;
    /**
     * 1、未删除2、删除
     */
	private Integer status;
    /**
     * 应用范围: 1-商家 2-平台
     */
	private Integer scope;
    /**
     * 资源id,有些菜单及按钮需要特定id以注册点击事件
     */
	@TableField("res_id")
	private String resId;
    /**
     * 资源图标,按钮资源可能需要指定图标
     */
	@TableField("res_icon")
	private String resIcon;
	/**
	 * 排序
	 */
	private Integer sorts;
	/**
	 * 跳转方式
	 */
	private String redirect;
    /**
     * 路由对象路径
     */
	private String path;
    /**
     * 路由组件
     */
	private String component;
    /**
     * 路由名称
     */
	private String name;
	/**
	 * 角色数组有该菜单权限 |分割
	 */
	@TableField("roles_db")
	private String rolesDb;
	@TableField(exist = false)
	private String[] roles;
    /**
     * 路由标题
     */
	private String title;
    /**
     * 路由图标
     */
	private String icon;
    /**
     * 是否固定 0-true 1-false
     */
	private Integer affix;
	/**
	 * 面包屑
	 */
	@TableField("breadcrumb")
	private Integer breadcrumb;
	/**
	 * 默认激活
	 */
	@TableField("active_menu")
	private String activeMenu;
    /**
     * 是否一直显示 0-true 1-false
     */
	@TableField("always_show")
	private Integer alwaysShow;
    /**
     * 是否缓存 0-true 1-false
     */
	@TableField("no_cache")
	private Integer noCache;
    /**
     * 是否正常显示 0-显示 1-删除
     */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 创建人id
     */
	@TableField("create_id")
	private String createId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
	// 非数据库属性
	/**
	 * 子节点
	 */
	@TableField(exist = false)
	private List<SystemResources> children;
	/**
	 * 菜单节点属性
	 */
	@TableField(exist = false)
	private Meta meta;
}
