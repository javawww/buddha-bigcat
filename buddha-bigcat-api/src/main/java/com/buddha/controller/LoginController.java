package com.buddha.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.constant.Constant;
import com.buddha.component.common.constant.RedisKeyConstant;
import com.buddha.component.common.dto.LoginAdminBean;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.Md5Util;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.param.SystemAdminParam;
import com.buddha.pojo.base.SystemAdmin;
import com.buddha.service.base.SystemAdminService;

import lombok.extern.log4j.Log4j2; 

/**
 * 登录Controller
 *
 * @version $Revision: 1.00 $
 */
@RestController
@RequestMapping("login")
@Log4j2
public class LoginController extends WebBaseController{
	
	@Autowired
	private SystemAdminService sysAdminService;
	
    /**
     * 登录验证
     */
    @RequestMapping("/dologin")
    public ResultJson dologin(@RequestBody SystemAdminParam param, HttpServletRequest request, Model model) {
        try {
        	if(StringUtils.isNull(param.getUsername())) {
        		return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "用户名为空");
        	}
        	if(StringUtils.isNull(param.getPassword())) {
        		return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "密码为空");
        	}else {
        		param.setPassword(Md5Util.getMD5String(param.getPassword()));
        	}
        	// 查询用户
        	QueryWrapper<SystemAdmin> queryWrapper = super.getQueryWrapper(SystemAdmin.class);
        	queryWrapper.getEntity().setName(param.getUsername());
        	queryWrapper.getEntity().setPassword(param.getPassword());
        	SystemAdmin systemAdmin = sysAdminService.getOne(queryWrapper);
        	if(StringUtils.isNull(systemAdmin)) {
        		return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST, "用户不存在");
        	}
        	// 密码置空
        	systemAdmin.setPassword(null);
        	LoginAdminBean loginAdminBean = new LoginAdminBean();
        	loginAdminBean.setLoginSource(param.getLoginSource());
        	loginAdminBean.setSysAdmin(systemAdmin);
        	// 角色设置
			 loginAdminBean.setRoles(new String[] {systemAdmin.getRoleName()});
			// 3.设置token及登录信息
			setLoginToken(loginAdminBean);
			// 修改为使用cookie作为token保存方式
			Cookie cookie = new Cookie(Constant.COOKIE_LOGIN_TOKEN, loginAdminBean.getToken());
			cookie.setPath("/");
			response.addCookie(cookie);
			 
        	return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, loginAdminBean);
        } catch (Exception e) {
        	log.error(e.getMessage());
        	return new ResultJson(e);
        }
    }

    /**
	 * 设置token
	 * @param loginUserInfoBean
	 */
	private void setLoginToken(LoginAdminBean loginAdminBean) {
		// 生成登录数据，保存并返回
		// 生成新token
		String tokenNew = "";
		//登录来源：1、web端  2、app端
		if(loginAdminBean.getLoginSource()!=null && loginAdminBean.getLoginSource()==2){
			tokenNew = RedisKeyConstant.APP_LOGIN_TOKEN_PRE + loginAdminBean.getSysAdmin().getId() + "_" + System.currentTimeMillis();
			loginAdminBean.setToken(tokenNew);
			// 删除失效的登录数据（通过前缀）
			redisService.delKeysLike(RedisKeyConstant.APP_LOGIN_TOKEN_PRE + loginAdminBean.getSysAdmin().getId());
		}else {
			tokenNew = RedisKeyConstant.WEB_LOGIN_TOKEN_PRE + loginAdminBean.getSysAdmin().getId() + "_" + System.currentTimeMillis();
			loginAdminBean.setToken(tokenNew);
			// 删除失效的登录数据（通过前缀）
			redisService.delKeysLike(RedisKeyConstant.WEB_LOGIN_TOKEN_PRE + loginAdminBean.getSysAdmin().getId());
		}
		// 登录成功保存用户信息
		redisService.set(tokenNew, JSON.toJSONString(loginAdminBean, Constant.JSON_DATE_FORMAT_CONF));
	}
	
	/**
	 * 登录 对象信息
	 * @param param
	 * @return
	 */
	@GetMapping("info")
	public ResultJson infoToJson(@RequestParam("token") String token) {
		try {
			if (StringUtils.isNull(token)) {
				log.info("token为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			LoginAdminBean loginBean = redisService.get(token, LoginAdminBean.class);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, loginBean);
		} catch (Exception e) {
			log.error("发生异常，异常信息：", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 *  注销
	 * @return
	 */
	@PostMapping("logout")
	public ResultJson logoutToJson() {
		try {
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, new String("success"));
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
}