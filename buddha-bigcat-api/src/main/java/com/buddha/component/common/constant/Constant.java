package com.buddha.component.common.constant;

import com.alibaba.fastjson.serializer.SerializerFeature;

public abstract class Constant {

	/**
	 * 项目的物理跟路径(项目启动的时候获取此值)
	 */
	public static String PROJECT_ROOT_DIR;

	/**
	 * cookie参数：登录会话key
	 */
	public static final String COOKIE_LOGIN_TOKEN = "ICBI_LOGIN_TOKEN";
	

	/**
	 * json时间格式化常量配置
	 * 
	 */
	public static final SerializerFeature JSON_DATE_FORMAT_CONF = SerializerFeature.WriteDateUseDateFormat;
	
	/**
	 * request中获取用户信息key
	 */
	public static final String REQUEST_LOGIN_USER_INFO = "REQUEST_LOGIN_USER_INFO";
	
	/**
	 * request中获取APP信息key
	 */
	public static final String REQUEST_APP_INFO = "REQUEST_APP_INFO";
	
}
