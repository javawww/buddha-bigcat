package com.buddha.controller.loan;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buddha.component.common.constant.Constant;
import com.buddha.component.common.constant.RedisKeyConstant;
import com.buddha.component.common.dto.LoginAdminBean;
import com.buddha.component.common.dto.Options;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.RandomUtil;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.controller.WebBaseController;
import com.buddha.param.AppConfigInfoParam;
import com.buddha.pojo.loan.AppConfigInfo;
import com.buddha.service.loan.AppConfigInfoService;
import com.buddha.service.market.AppShareInfoService;

import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * APP设置 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-08
 */
@RestController
@RequestMapping("appConfigInfo")
@Log4j2
public class AppConfigInfoController extends WebBaseController {


    @Autowired
    private AppConfigInfoService appConfigInfoService;

    @Autowired
    private AppShareInfoService appShareInfoService;
    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListAppConfigInfo(@RequestBody AppConfigInfoParam param){
        try {
        	QueryWrapper<AppConfigInfo> queryWrapper = super.getQueryWrapper(AppConfigInfo.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getCodeName())) {
        		queryWrapper.like("code_name", param.getCodeName());
        	}
        	if(StringUtils.isNotNull(param.getName())) {
        		queryWrapper.like("name", param.getName());
        	}
        	if(StringUtils.isNotNull(param.getLegalMan())) {
        		queryWrapper.like("legal_man", param.getLegalMan());
        	}
        	// 过滤登录人数据
        	queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<AppConfigInfo> page = new Page<AppConfigInfo>(param.getPage(), param.getPageSize());
            page = appConfigInfoService.page(page, queryWrapper);
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, page);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }


    /**
              *  全部数据
     */
    @PostMapping("list")
    public ResultJson listAppConfigInfo(@RequestBody AppConfigInfoParam param){
        try {
        	QueryWrapper<AppConfigInfo> queryWrapper = super.getQueryWrapper(AppConfigInfo.class);
        	// [查询条件]根据业务补充...
        	queryWrapper.orderByDesc("create_time");
        	queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	
            List<AppConfigInfo> list = appConfigInfoService.list(queryWrapper);
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }


    /**
             * 详情
     */
    @PostMapping("detail")
    public ResultJson detailAppConfigInfo(@RequestBody AppConfigInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            AppConfigInfo detail = appConfigInfoService.getById(param.getId());
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, detail);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
    
    /**
	     * 选中一款APP，放到REDIS缓存中，关联当前登录会员
	*/
	@PostMapping("chooseApp")
	public ResultJson chooseAppConfigInfo(@RequestBody AppConfigInfoParam param){
		try {
		    // [判断条件]根据业务补充...
			if (StringUtils.isNull(param.getId())) {
		        log.info("id为空");
		        return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
		    }
			
		    AppConfigInfo detail = appConfigInfoService.getById(param.getId());
		    if(StringUtils.isNull(detail)) {
		    	return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST);
		    }else {
		    	setAppInfoToken(detail);
		    }
		    return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, detail);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
		    return new ResultJson(e);
		}
	}
	
	 /**
	     *  获取APPINFO
	*/
	@PostMapping("queryAppinfoByToken")
	public ResultJson queryAppInfoByTokenKey(@RequestBody AppConfigInfoParam param) {
		try {
			if(StringUtils.isNull(param.getToken())) {
				log.info("token key 为空");
		        return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"token key为空");
			}
			AppConfigInfo appConfigInfo = redisService.get(param.getToken(), AppConfigInfo.class);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, appConfigInfo);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
		    return new ResultJson(e);
		}
	}
	
	/**
	 * 设置token
	 * @param loginUserInfoBean
	 */
	private void setAppInfoToken(AppConfigInfo appInfo) {
		// 生成新token
		String tokenNew = "";
		tokenNew = RedisKeyConstant.WEB_APPINFO_TOKEN_PRE + appInfo.getCodeName() + "_" + System.currentTimeMillis();
		appInfo.setToken(tokenNew);
		// 删除失效的登录数据（通过前缀）
		redisService.delKeysLike(RedisKeyConstant.WEB_APPINFO_TOKEN_PRE + appInfo.getCodeName());
		// 登录成功保存用户信息
		redisService.set(tokenNew, JSON.toJSONString(appInfo, Constant.JSON_DATE_FORMAT_CONF));
	}
    /**
              * 保存
     */
    @PostMapping("save")
    public ResultJson saveAppConfigInfo(@RequestBody AppConfigInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if(StringUtils.isNull(param.getCodeName())) {
        		//代号
        		param.setCodeName(super.systemAdminId.toString() + "_" + RandomUtil.getRandomStrByLength(6));
        	}
        	Date curDate = new Date();
        	AppConfigInfo appConfigInfo = new AppConfigInfo();
            BeanUtils.copyProperties(param, appConfigInfo);
            appConfigInfo.setCreateTime(curDate);
            appConfigInfo.setUpdateTime(curDate);
            appConfigInfo.setCreateId(super.systemAdminId.toString());
            appConfigInfoService.save(appConfigInfo);
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }


    /**
             * 更新
     */
    @PostMapping("update")
    public ResultJson updateAppConfigInfo(@RequestBody AppConfigInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	AppConfigInfo appConfigInfo = new AppConfigInfo();
            BeanUtils.copyProperties(param, appConfigInfo);
            appConfigInfo.setUpdateTime(curDate);
            appConfigInfoService.updateById(appConfigInfo);
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
     }


    /**
             * 删除
     */
    @RequestMapping("del")
    public ResultJson delAppConfigInfo(@RequestBody AppConfigInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            appConfigInfoService.removeById(param.getId());
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
    
    /**
	 * 下拉选项
	 * @param param
	 * @return
	 */
	@PostMapping("options")
	public ResultJson options(@RequestBody AppConfigInfoParam param) {
		try {
			if(StringUtils.isNull(param.getCreateId())) {
				param.setCreateId(super.systemAdminId.toString());
			}
			// 获取自己APP
			List<Options> list = appConfigInfoService.queryOptions(param);
			// 获取共享APP
			List<Options> sharelist =appShareInfoService.queryShareOptions(param);
			for (Options options : sharelist) {
				options.setLabel(options.getLabel()+"[共享]");
			}
			list.addAll(sharelist);
			
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
