package com.buddha.service.member;

import java.util.List;

import com.buddha.pojo.member.MemberInfo;
import com.buddha.mapper.member.MemberInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.buddha.component.common.dto.Options;
import com.buddha.mapper.member.MemberInfoMapper;
import com.buddha.param.MemberInfoParam;

 /**
 * 
 * 会员基本信息 服务实现类
 *
 * ${jinghao}
 *
 * CopyRight (C) 2018 ShenZhen LoveJava Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 小程序开发，企业系统开发，安卓苹果APP开发，服务器部署<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * ${jinghao}
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2019-06-13
 * @版权 一群热爱技术的程序猿
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@Service
@DS("bigcat_loan")
public class MemberInfoService extends ServiceImpl<MemberInfoMapper, MemberInfo> {
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	public List<Options> queryOptions(MemberInfoParam param) {
		return memberInfoMapper.queryOptions(param);
	}
	
}
