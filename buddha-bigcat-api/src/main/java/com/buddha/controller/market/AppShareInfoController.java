package com.buddha.controller.market;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.buddha.controller.WebBaseController;
import com.buddha.service.loan.AppConfigInfoService;
import com.buddha.service.market.AppShareInfoService;
import com.buddha.pojo.loan.AppConfigInfo;
import com.buddha.pojo.market.AppShareInfo;
import com.buddha.param.AppShareInfoParam;
import com.buddha.component.common.dto.Options;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.enums.StatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 共享App申请 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-17
 */
@RestController
@RequestMapping("appShareInfo")
@Log4j2
public class AppShareInfoController extends WebBaseController {


    @Autowired
    private AppShareInfoService appShareInfoService;

    @Autowired
    private AppConfigInfoService appConfigInfoService;

    /**
	  * 分页 APP共享申请
	 */
	@PostMapping("pageList")
    public ResultJson pageListAppShareInfo(@RequestBody AppShareInfoParam param){
        try {
        	QueryWrapper<AppShareInfo> queryWrapper = super.getQueryWrapper(AppShareInfo.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getAppCode())) {
        		queryWrapper.like("app_code", param.getAppCode());
        	}
        	if(StringUtils.isNotNull(param.getName())) {
        		queryWrapper.like("name", param.getName());
        	}
        	queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
//        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<AppShareInfo> page = new Page<AppShareInfo>(param.getPage(), param.getPageSize());
            page = appShareInfoService.page(page, queryWrapper);
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, page);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
	
	/**
	 * 分页 APP共享审核
	 */
	@PostMapping("pageListAudit")
	public ResultJson pageListAuditAppShareInfo(@RequestBody AppShareInfoParam param){
		try {
			QueryWrapper<AppShareInfo> queryWrapper = super.getQueryWrapper(AppShareInfo.class);
			// [查询条件]根据业务补充...
			if(StringUtils.isNotNull(param.getAppCode())) {
				queryWrapper.like("app_code", param.getAppCode());
			}
			if(StringUtils.isNotNull(param.getName())) {
				queryWrapper.like("name", param.getName());
			}
//			queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
			// 排序
			queryWrapper.orderByAsc("sorts");
			queryWrapper.orderByDesc("create_time");
			
			IPage<AppShareInfo> page = new Page<AppShareInfo>(param.getPage(), param.getPageSize());
			page = appShareInfoService.page(page, queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, page);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}


    /**
     * 全部数据
     */
    @PostMapping("list")
    public ResultJson listAppShareInfo(@RequestBody AppShareInfoParam param){
        try {
        	QueryWrapper<AppShareInfo> queryWrapper = super.getQueryWrapper(AppShareInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	//排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<AppShareInfo> list = appShareInfoService.list(queryWrapper);
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
    public ResultJson detailAppShareInfo(@RequestBody AppShareInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            AppShareInfo detail = appShareInfoService.getById(param.getId());
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, detail);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }


    /**
     * 保存
     */
    @PostMapping("save")
    public ResultJson saveAppShareInfo(@RequestBody AppShareInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	QueryWrapper<AppConfigInfo> queryWrapper = super.getQueryWrapper(AppConfigInfo.class);
        	queryWrapper.getEntity().setCodeName(param.getAppCode());//APP代号
        	AppConfigInfo appConfigInfo = appConfigInfoService.getOne(queryWrapper);
        	if(StringUtils.isNull(appConfigInfo)) {
        		return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST, "APP代号错误");
        	}else {
        		param.setName(appConfigInfo.getName());//APP名称
        		param.setAppId(appConfigInfo.getId());
        	}
        	// 是否已经申请过
        	QueryWrapper<AppShareInfo> queryWrapper2 = super.getQueryWrapper(AppShareInfo.class);
        	queryWrapper2.getEntity().setAppCode(param.getAppCode());
        	queryWrapper2.getEntity().setCreateId(super.systemAdminId.toString());
        	AppShareInfo appShareInfo2 = appShareInfoService.getOne(queryWrapper2);
        	if(StringUtils.isNotNull(appShareInfo2)) {
        		return new ResultJson(ResultStatusEnum.DATA_IS_EXIST, "已经申请过了");
        	}
        	Date curDate = new Date();
        	AppShareInfo appShareInfo = new AppShareInfo();
            BeanUtils.copyProperties(param, appShareInfo);
            appShareInfo.setCreateTime(curDate);
            appShareInfo.setUpdateTime(curDate);
            appShareInfo.setCreateId(super.systemAdminId.toString());
//            appShareInfo.setAppId(super.appInfoId);
            appShareInfo.setStatus(StatusEnum.AUDITING.getValue());//审核中
            appShareInfoService.save(appShareInfo);
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
    public ResultJson updateAppShareInfo(@RequestBody AppShareInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	AppShareInfo appShareInfo = new AppShareInfo();
            BeanUtils.copyProperties(param, appShareInfo);
            appShareInfo.setUpdateTime(curDate);
            appShareInfoService.updateById(appShareInfo);
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
    public ResultJson delAppShareInfo(@RequestBody AppShareInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            appShareInfoService.removeById(param.getId());
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
    
    /**
	 * 下拉选项
	 */
	@PostMapping("options")
	public ResultJson options(@RequestBody AppShareInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				param.setCreateId(super.appInfoId);
			}
			List<Options> list = appShareInfoService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	
	/**
     * 通过
     */
    @RequestMapping("pass")
    public ResultJson passAppShareInfo(@RequestBody AppShareInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }else {
            	AppShareInfo appShareInfo = appShareInfoService.getById(param.getId());
            	appShareInfo.setStatus(StatusEnum.PASS.getValue());
            	appShareInfo.setUpdateTime(new Date());
            	appShareInfoService.updateById(appShareInfo);
            }
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
    
    /**
     * 拒绝
     */
    @RequestMapping("refuse")
    public ResultJson refuseAppShareInfo(@RequestBody AppShareInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }else {
            	AppShareInfo appShareInfo = appShareInfoService.getById(param.getId());
            	appShareInfo.setStatus(StatusEnum.REFUSE.getValue());
            	appShareInfo.setUpdateTime(new Date());
            	appShareInfoService.updateById(appShareInfo);
            }
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
}
