package com.buddha.param;

import lombok.Getter;
import lombok.Setter;
/**
 * 菜单参数
 * @author chuck
 *
 */
@Getter
@Setter
public class SystemResourcesParam extends BaseParam{
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
	private String resId;
    /**
     * 资源图标,按钮资源可能需要指定图标
     */
	private String resIcon;
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
	private String rolesDb;
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
	private Integer breadcrumb;
	/**
	 * 默认激活
	 */
	private String activeMenu;
    /**
     * 是否一直显示 0-true 1-false
     */
	private Integer alwaysShow;
    /**
     * 是否缓存 0-true 1-false
     */
	private Integer noCache;

}
