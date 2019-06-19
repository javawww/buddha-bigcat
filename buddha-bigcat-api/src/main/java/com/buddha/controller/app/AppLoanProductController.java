package com.buddha.controller.app;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.IPUtils;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.controller.AppBaseController;
import com.buddha.param.LoanApplyProductParam;
import com.buddha.pojo.loan.LoanApplyProduct;
import com.buddha.service.loan.LoanApplyProductService;

import lombok.extern.log4j.Log4j2;

/**
 * APP端[产品管理]
 * @author chuck
 *
 */
@CrossOrigin(origins = {"http://192.168.8.178:9004","https://miraclewap.ywq157.com"}, maxAge = 3600)
@RestController("AppLoanProductController")
@RequestMapping("app/appLoanProductController")
@Log4j2
public class AppLoanProductController extends AppBaseController{

	@Autowired
    private LoanApplyProductService loanApplyProductService;
	
	 /**
     * 保存
     */
    @PostMapping("saveApply")
    public ResultJson saveLoanApplyProduct(LoanApplyProductParam param){
        try {
        	if(StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}else {
				//判断同一个手机号同一个产品
				QueryWrapper<LoanApplyProduct> queryWrapper = super.getQueryWrapper(LoanApplyProduct.class);
				queryWrapper.getEntity().setAppId(param.getAppId());
				queryWrapper.getEntity().setMobile(param.getMobile());
				LoanApplyProduct one = loanApplyProductService.getOne(queryWrapper);
				if(StringUtils.isNotNull(one)) {
					return new ResultJson(ResultStatusEnum.DATA_IS_EXIST, "该手机号已经使用过了");
				}
			}
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	LoanApplyProduct loanApplyProduct = new LoanApplyProduct();
            BeanUtils.copyProperties(param, loanApplyProduct);
            loanApplyProduct.setIpAddress(IPUtils.getClientIP(super.request));
            loanApplyProduct.setCreateTime(curDate);
            loanApplyProduct.setUpdateTime(curDate);
            loanApplyProductService.save(loanApplyProduct);
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
	
}
