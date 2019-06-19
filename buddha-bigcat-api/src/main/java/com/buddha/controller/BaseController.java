package com.buddha.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.buddha.component.common.constant.Constant;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseController {

	// ##################################页面使用对应引用定义#####################################
	protected static final String MSG = "msg";

	protected static final String INDEX = "index";

	protected static final String OBJECT = "object";

	protected static final String LIST = "list";

	protected static final String PAGE = "page";

	// #####################################################################################
	
	/**
	 * request对象
	 */
	protected HttpServletRequest request;

	/**
	 * session对象
	 */
	protected HttpSession session;

	/**
	 * response对象
	 */
	protected HttpServletResponse response;

	/**
	 * cookie Map结构
	 */
	protected Map<String, Cookie> cookieMap;

	/**
	 * application对象
	 */
	protected ServletContext application;

	/**
	 * spring容器对象
	 */
	protected WebApplicationContext wac;
	
	
	/**
	 * IP地址正则
	 */
	private static final String PATTERN_IP = "(\\d*\\.){3}\\d*";
	
	
	private static final Pattern ipPattern = Pattern.compile(PATTERN_IP);

	/**
	 * 项目基础路径地址
	 */
	protected String basePath;

	public BaseController() {
		wac = ContextLoader.getCurrentWebApplicationContext();
	}

	/**
	 * 获取 request，response，session以及servletContext(此方法在控制器的方法执行前执行)
	 * 
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setObject(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		session = this.request.getSession();
//		application = this.wac.getServletContext();
		cookieMap = this.getCookieMap(request);
		String port = request.getServerPort() == 80 ? "" : (":" + request.getServerPort());
		basePath = (ipPattern.matcher(request.getServerName()).find() ? request.getScheme() : "https") + "://" + request.getServerName() + port + request.getContextPath();
		request.setAttribute("basePath", basePath);
	}

	/**
	 * 获取客户端的cookie，按照名称封装到map结构中
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Cookie> getCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 写入json字符串到客户端（content-type=application/json）
	 * 
	 * @param obj
	 *            数据对象
	 */
	protected void writerJson(Object data) {
		this.writer("application/json;charset=UTF-8", JSON.toJSONString(data, Constant.JSON_DATE_FORMAT_CONF));
	}

	/**
	 * 写入文字到客户端
	 * 
	 * @param content
	 *            数据内容
	 */
	protected void writerTxt(String content) {
		this.writer("text/html;charset=UTF-8", content);
	}

	/**
	 * 写入数据到客户端
	 * 
	 * @param contentType
	 *            类型
	 * @param content
	 *            数据内容
	 */
	protected void writer(String contentType, String content) {
		try {
			response.setContentType(contentType);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			log.error("写入数据错误，错误信息：", e);
		}
	}
}
