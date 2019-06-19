package com.buddha.controller.app;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.IPUtils;
import com.buddha.component.common.utils.RandomUtil;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.controller.AppBaseController;
import com.buddha.controller.WebBaseController;
import com.buddha.controller.market.MarketCarouselInfoController;
import com.buddha.param.AppConfigInfoParam;
import com.buddha.param.MemberInfoParam;
import com.buddha.param.MemberLogsInfoParam;
import com.buddha.pojo.member.MemberInfo;
import com.buddha.pojo.member.MemberLogsInfo;
import com.buddha.service.member.MemberInfoService;
import com.buddha.service.member.MemberLogsInfoService;

import lombok.extern.log4j.Log4j2;
/**
 * APP端[会员管理]
 * @author chuck
 *
 */
@CrossOrigin(origins = {"http://192.168.8.178:9004","https://miraclewap.ywq157.com"}, maxAge = 3600)
@RestController("AppMemberController")
@RequestMapping("app/appMemberController")
@Log4j2
public class AppMemberController extends AppBaseController{
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private MemberLogsInfoService memberLogsInfoService;
	
	/**
	 * 注册会员
	 * @param param
	 * @return
	 */
	@PostMapping("doRegisterMember")
	public ResultJson doRegisterMember(MemberInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			Date curDate = new Date();
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setRecommendCode(RandomUtil.getUUID());
			memberInfo.setCreateTime(curDate);
			memberInfo.setUpdateTime(curDate);
			memberInfo.setMobile(param.getMobile());
			memberInfo.setName(param.getName());
			memberInfo.setAppId(param.getAppId());
			memberInfoService.save(memberInfo);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, (Object)memberInfo.getId());
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
	}
	
	/**
	 * 会员详情
	 * @param param
	 * @return
	 */
	@PostMapping("detailMemberById")
	public ResultJson detailMemberById(MemberInfoParam param) {
		try {
			if(StringUtils.isNull(param.getId())) {
				log.error("主键为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "主键为空");
			}
			if(StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			QueryWrapper<MemberInfo> queryWrapper = super.getQueryWrapper(MemberInfo.class);
			queryWrapper.getEntity().setId(param.getId());
			queryWrapper.getEntity().setAppId(param.getAppId());
			MemberInfo memberInfo = memberInfoService.getOne(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, memberInfo);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
	}
	
	/**
	 * 会员访问日志
	 * @param param
	 * @return
	 */
	@PostMapping("saveMemberLogInfo")
	public ResultJson saveMemberLogInfo(MemberLogsInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			Date curDate = new Date();
			MemberLogsInfo logsInfo = new MemberLogsInfo();
			BeanUtils.copyProperties(param, logsInfo);
			logsInfo.setIpAddress(IPUtils.getClientIP(super.request));
			logsInfo.setCreateTime(curDate);
			logsInfo.setUpdateTime(curDate);
			memberLogsInfoService.save(logsInfo);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
	}
}
