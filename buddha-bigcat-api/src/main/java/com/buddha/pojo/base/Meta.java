package com.buddha.pojo.base;

import lombok.Getter;
import lombok.Setter;

/**
 * 路由元数据
 * @author Administrator
 *
 */
@Getter
@Setter
public class Meta {

	private String[] roles;
	private String title;
	private String icon;
	private Boolean noCache;
	private Boolean affix;
	private Boolean breadcrumb;
	private String activeMenu;
}
