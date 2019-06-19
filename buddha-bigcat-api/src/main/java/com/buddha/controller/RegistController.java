package com.buddha.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.Md5Util;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.param.SystemAdminParam;
import com.buddha.pojo.base.SystemAdmin;
import com.buddha.service.base.SystemAdminService;

import lombok.extern.log4j.Log4j2;

/**
 * 注册
 * @author chuck
 *
 */
@RestController
@RequestMapping("regist")
@Log4j2
public class RegistController extends WebBaseController{

	@Autowired
	private SystemAdminService sysAdminService;
	
	@PostMapping("doregist")
	public ResultJson doregist(@RequestBody SystemAdminParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getUsername())) {
        		return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "用户名为空");
        	}
			if(StringUtils.isNull(param.getNickname())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "昵称为空");
			}
			if(StringUtils.isNull(param.getAvatar())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "头像为空");
			}
        	if(StringUtils.isNull(param.getPassword())) {
        		return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "密码为空");
        	}else {
        		param.setPassword(Md5Util.getMD5String(param.getPassword()));
        	}
        	Date curDate = new Date();
        	// 保存
        	SystemAdmin sysAdmin = new SystemAdmin();
        	BeanUtils.copyProperties(param, sysAdmin);
        	sysAdmin.setName(param.getUsername());// 登录用户名
        	sysAdmin.setCreateTime(curDate);
        	sysAdmin.setBalance(new BigDecimal(1)); // 注册送余额1元
        	sysAdmin.setBalancePwd(param.getPassword()); // 余额密码和登录密码一样 备用
        	sysAdmin.setIntegral(50);// 注册送50金币
        	// 角色设置
        	sysAdmin.setRoleId(3); // 角色id
        	sysAdmin.setRoleCode("loanManager");// 商家
        	sysAdmin.setRoleName("贷超管理员");
        	sysAdminService.save(sysAdmin);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	
}
