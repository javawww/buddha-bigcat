package com.buddha.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.dto.Options;
import com.buddha.mapper.base.SystemRolesMapper;
import com.buddha.param.SystemRolesParam;
import com.buddha.pojo.base.SystemRoles;


 /**
 * 
 * 角色表 服务实现类
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
@Service
@DS("bigcat_base")
public class SystemRolesService extends ServiceImpl<SystemRolesMapper, SystemRoles> {
	
	@Autowired
	private SystemRolesMapper rolesMapper;
	
	public Integer[] checkedResArr(SystemRolesParam param) {
		return rolesMapper.checkedResArr(param);
	}

	public List<Options> queryOptions(SystemRolesParam param) {
		return rolesMapper.queryOptions(param);
	}
	
}
