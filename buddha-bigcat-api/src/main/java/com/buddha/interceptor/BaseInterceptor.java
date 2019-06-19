package com.buddha.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.buddha.component.common.constant.Constant;

import lombok.extern.log4j.Log4j2;
/**
 * 拦截器基础封装
 * @author bigcat
 *
 */
@Log4j2
@Component
public abstract class BaseInterceptor extends HandlerInterceptorAdapter{

	/**
	 * 系统token标志
	 */
	protected final static String TOKEN = "token";

	/**
	 * 写入json字符串到客户端（content-type=application/json）
	 * 
	 * @param obj
	 *            数据对象
	 */
	protected void writerJson(HttpServletResponse response, Object data) {
		this.writer(response, "application/json;charset=UTF-8", JSON.toJSONString(data, Constant.JSON_DATE_FORMAT_CONF));
	}

	/**
	 * 写入文字到客户端
	 * 
	 * @param content
	 *            数据内容
	 */
	protected void writerTxt(HttpServletResponse response, String content) {
		this.writer(response, "text/html;charset=UTF-8", content);
	}

	/**
	 * 写入数据到客户端
	 * 
	 * @param contentType
	 *            类型
	 * @param content
	 *            数据内容
	 */
	protected void writer(HttpServletResponse response, String contentType, String content) {
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
