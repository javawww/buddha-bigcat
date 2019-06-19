package com.buddha.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.utils.StringUtils;

import lombok.extern.log4j.Log4j2;
/**
 * 移动端基控制器
 * @author bigcat
 *
 */
@Log4j2
public class AppBaseController extends BaseController{

	/**
	 * APP应用代号
	 */
	protected String appCode;
	/**
	 * 获取 request，response，session以及servletContext(此方法在控制器的方法执行前执行)
	 * 
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setObject(HttpServletRequest request, HttpServletResponse response) {
		// 调用父类参数设置方法
		super.setObject(request, response);
		//移动端应用标识
		appCode = request.getHeader("appCode");
		// 调用父类参数设置方法
		super.setObject(request, response);
	}

	
	/**
	 * 获取对应类型的QueryWrapper对象（已经设置好entity对象，如果不设置entity对象，调用entity的属性就会空指针）
	 * @param classz
	 * @return
	 */
	protected <T> QueryWrapper<T> getQueryWrapper(Class<T> classz){
		try {
			QueryWrapper<T> ew = new QueryWrapper<T>(classz.newInstance());
			return ew;
		} catch (Exception ex) {
		}
		return null;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// date,datetime
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (StringUtils.isEmpty(value)) {
					setValue(null);
					return;
				}
				try {
					if (value.length() == 10) {
						setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
					} else if (value.length() == 8) {
						setValue(new SimpleDateFormat("HH:mm:ss").parse(value));
					} else if (value.length() == 16) {
						setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(value));
					} else {
						setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
					}

				} catch (ParseException e) {
					log.error("Can not convert '" + value + "' to java.util.Date", e);
				}
			}

			public String getAsText() {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
			}

		});
		// int
		binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (StringUtils.isEmpty(value)) {
					setValue(0);
					return;
				}
				setValue(Integer.parseInt(value));
			}

			public String getAsText() {
				return getValue().toString();
			}

		});

		// long
		binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (StringUtils.isEmpty(value)) {
					setValue(0);
					return;
				}
				setValue(Long.parseLong(value));
			}

			public String getAsText() {
				return getValue().toString();
			}

		});

		// double
		binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (StringUtils.isEmpty(value)) {
					setValue(0);
					return;
				}
				setValue(Double.parseDouble(value));
			}

			public String getAsText() {
				return getValue().toString();
			}

		});
	}
}
