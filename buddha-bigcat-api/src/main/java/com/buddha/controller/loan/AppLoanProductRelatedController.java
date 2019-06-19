package com.buddha.controller.loan;
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
import com.buddha.service.loan.AppLoanProductRelatedService;
import com.buddha.service.loan.LoanMarketProductService;
import com.buddha.pojo.loan.AppConfigInfo;
import com.buddha.pojo.loan.AppLoanProductRelated;
import com.buddha.pojo.loan.LoanMarketProduct;
import com.buddha.param.AppLoanProductRelatedParam;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-08
 */
@RestController
@RequestMapping("appLoanProductRelated")
@Log4j2
public class AppLoanProductRelatedController extends WebBaseController {


    @Autowired
    private AppLoanProductRelatedService appLoanProductRelatedService;
    
    @Autowired
    private AppConfigInfoService appConfigInfoService;

    @Autowired
    private LoanMarketProductService loanMarketProductService;

    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListAppLoanProductRelated(@RequestBody AppLoanProductRelatedParam param){
        try {
        	QueryWrapper<AppLoanProductRelated> queryWrapper = super.getQueryWrapper(AppLoanProductRelated.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getAppId())) {
        		queryWrapper.like("app_id", param.getAppId());
        	}
        	if(StringUtils.isNotNull(param.getLoanProductId())) {
        		queryWrapper.like("loan_product_id", param.getLoanProductId());
        	}
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<AppLoanProductRelated> page = new Page<AppLoanProductRelated>(param.getPage(), param.getPageSize());
            page = appLoanProductRelatedService.page(page, queryWrapper);
            List<AppLoanProductRelated> list = page.getRecords();
            if(StringUtils.isNotNull(list)) {
            	for (AppLoanProductRelated appLoanProductRelated : list) {
            		// APP应用信息
            		AppConfigInfo appConfigInfo = appConfigInfoService.getById(appLoanProductRelated.getAppId());
            		appLoanProductRelated.setAppConfigInfo(appConfigInfo);
            		// 贷超产品信息
            		LoanMarketProduct loanMarketProduct = loanMarketProductService.getById(appLoanProductRelated.getLoanProductId());
            		appLoanProductRelated.setLoanMarketProduct(loanMarketProduct);
				}
            }
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
    public ResultJson listAppLoanProductRelated(@RequestBody AppLoanProductRelatedParam param){
        try {
        	QueryWrapper<AppLoanProductRelated> queryWrapper = super.getQueryWrapper(AppLoanProductRelated.class);
        	// [查询条件]根据业务补充...
        	queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.orderByDesc("create_time");
        	
            List<AppLoanProductRelated> list = appLoanProductRelatedService.list(queryWrapper);
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
    public ResultJson detailAppLoanProductRelated(@RequestBody AppLoanProductRelatedParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            AppLoanProductRelated detail = appLoanProductRelatedService.getById(param.getId());
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
    public ResultJson saveAppLoanProductRelated(@RequestBody AppLoanProductRelatedParam param){
        try {
            // [判断条件]根据业务补充...
        	String[] loanProductIdArr = null;
        	if(StringUtils.isNull(param.getLoanProductId())) {
        		return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "贷超产品为空");
        	}else {
        		loanProductIdArr = StringUtils.string2List(param.getLoanProductId());
        	}
    		// 清空该APP关联的所有贷超产品
    		QueryWrapper<AppLoanProductRelated> queryWrapper = super.getQueryWrapper(AppLoanProductRelated.class);
//        		queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
    		queryWrapper.getEntity().setAppId(super.appInfoId);
    		appLoanProductRelatedService.remove(queryWrapper);
        	Date curDate = new Date();
        	// 循环保存
        	for (String loanProductId : loanProductIdArr) {
        		AppLoanProductRelated appLoanProductRelated = new AppLoanProductRelated();
        		BeanUtils.copyProperties(param, appLoanProductRelated);
        		appLoanProductRelated.setAppId(super.appInfoId);
        		appLoanProductRelated.setLoanProductId(loanProductId);
        		appLoanProductRelated.setCreateTime(curDate);
        		appLoanProductRelated.setUpdateTime(curDate);
        		appLoanProductRelated.setCreateId(super.systemAdminId.toString());
        		appLoanProductRelatedService.save(appLoanProductRelated);
			}
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
    public ResultJson updateAppLoanProductRelated(@RequestBody AppLoanProductRelatedParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	AppLoanProductRelated appLoanProductRelated = new AppLoanProductRelated();
            BeanUtils.copyProperties(param, appLoanProductRelated);
            appLoanProductRelated.setUpdateTime(curDate);
            appLoanProductRelatedService.updateById(appLoanProductRelated);
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
    public ResultJson delAppLoanProductRelated(@RequestBody AppLoanProductRelatedParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            appLoanProductRelatedService.removeById(param.getId());
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
}
