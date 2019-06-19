package com.buddha.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.buddha.component.common.constant.Constant;
import com.buddha.component.common.dto.LoginAdminBean;
import com.buddha.component.common.enums.NoTokenVerifyURIEnum;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.pojo.loan.AppConfigInfo;
import com.buddha.service.common.RedisService;

import lombok.extern.log4j.Log4j2;
/**
 * token授权拦截器（此拦截器将拦截所有的数据访问接口，访问接口是必须在header中提供token参数，否则直接拦截返回）
 * @author bigcat
 *
 */
@Log4j2
@Component
public class TokenInterceptor extends BaseInterceptor {
	
	@Autowired
	RedisService redisService;

	/**
	 * 在业务处理器处理请求之前被调用
	 * 
	 * 返回true执行下一个拦截器或者控制器，返回flase，拦截退出
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		try {
			for (NoTokenVerifyURIEnum uri : NoTokenVerifyURIEnum.values()) {
				if (uri.getValue().equals(request.getRequestURI()) || request.getRequestURI().contains(uri.getValue())) {
					return true;
				}
			}
			// 区块1
			String token = request.getHeader("token");
			if (StringUtils.isNull(token)) {
				log.info("token为空，拦截器拦截");
				notLoginHandler(request, response);
				return false;
			}
			// 获取登录用户信息
			String tokenContentObjStr = redisService.get(token);
			if (StringUtils.isNull(tokenContentObjStr)) {
				log.info("token不存在，拦截器拦截");
				notLoginHandler(request, response);
				return false;
			}
			LoginAdminBean loginAdminBean = JSONObject.parseObject(tokenContentObjStr, LoginAdminBean.class);
			request.setAttribute(Constant.REQUEST_LOGIN_USER_INFO, loginAdminBean);
			// 区块2
			String tokenApp = request.getHeader("tokenApp"); // APP TOKEN
			if(StringUtils.isNotNull(tokenApp)) {
				String tokenAppInfoStr = redisService.get(tokenApp);
				if(StringUtils.isNotNull(tokenAppInfoStr)) {
					AppConfigInfo appConfigInfo = JSONObject.parseObject(tokenAppInfoStr, AppConfigInfo.class);
					request.setAttribute(Constant.REQUEST_APP_INFO, appConfigInfo);
				}
			}
			
			return true;
		} catch (Exception ex) {
			log.error("系统异常：", ex);
		}
		notLoginHandler(request, response);
		return false;
	}

	/**
	 * 未登录拦截操作
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void notLoginHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestUri = request.getRequestURI();
		log.info("拦截到未登录访问，已做跳转登录处理；访问地址：" + requestUri);
		writerJson(response, new ResultJson(ResultStatusEnum.TOKEN_IS_EXPIRE));
	}
}
