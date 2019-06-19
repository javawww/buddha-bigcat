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
import com.buddha.service.loan.LoanApplyProductService;
import com.buddha.service.loan.LoanMarketProductService;
import com.buddha.pojo.loan.LoanApplyProduct;
import com.buddha.pojo.loan.LoanMarketProduct;
import com.buddha.param.LoanApplyProductParam;
import com.buddha.component.common.dto.Options;

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
 * @since 2019-06-18
 */
@RestController
@RequestMapping("loanApplyProduct")
@Log4j2
public class LoanApplyProductController extends WebBaseController {


    @Autowired
    private LoanApplyProductService loanApplyProductService;

    @Autowired
    private LoanMarketProductService loanMarketProductService;
    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListLoanApplyProduct(@RequestBody LoanApplyProductParam param){
        try {
        	QueryWrapper<LoanApplyProduct> queryWrapper = super.getQueryWrapper(LoanApplyProduct.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getMobile())) {
        		queryWrapper.like("mobile", param.getMobile());
        	}
        	if(StringUtils.isNotNull(param.getIpAddress())) {
        		queryWrapper.like("ip_address", param.getIpAddress());
        	}
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<LoanApplyProduct> page = new Page<LoanApplyProduct>(param.getPage(), param.getPageSize());
            page = loanApplyProductService.page(page, queryWrapper);
            List<LoanApplyProduct> list = page.getRecords();
            for (LoanApplyProduct loanApplyProduct : list) {
            	LoanMarketProduct loanMarketProduct = loanMarketProductService.getById(loanApplyProduct.getLoanProductId());
            	loanApplyProduct.setLoanMarketProduct(loanMarketProduct);
			}
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
    public ResultJson listLoanApplyProduct(@RequestBody LoanApplyProductParam param){
        try {
        	QueryWrapper<LoanApplyProduct> queryWrapper = super.getQueryWrapper(LoanApplyProduct.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	//排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<LoanApplyProduct> list = loanApplyProductService.list(queryWrapper);
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
    public ResultJson detailLoanApplyProduct(@RequestBody LoanApplyProductParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            LoanApplyProduct detail = loanApplyProductService.getById(param.getId());
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
    public ResultJson saveLoanApplyProduct(@RequestBody LoanApplyProductParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	LoanApplyProduct loanApplyProduct = new LoanApplyProduct();
            BeanUtils.copyProperties(param, loanApplyProduct);
            loanApplyProduct.setCreateTime(curDate);
            loanApplyProduct.setUpdateTime(curDate);
            loanApplyProduct.setCreateId(super.systemAdminId.toString());
            loanApplyProduct.setAppId(super.appInfoId);
            loanApplyProductService.save(loanApplyProduct);
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
    public ResultJson updateLoanApplyProduct(@RequestBody LoanApplyProductParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	LoanApplyProduct loanApplyProduct = new LoanApplyProduct();
            BeanUtils.copyProperties(param, loanApplyProduct);
            loanApplyProduct.setUpdateTime(curDate);
            loanApplyProductService.updateById(loanApplyProduct);
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
    public ResultJson delLoanApplyProduct(@RequestBody LoanApplyProductParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            loanApplyProductService.removeById(param.getId());
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
	public ResultJson options(@RequestBody LoanApplyProductParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				param.setCreateId(super.appInfoId);
			}
			List<Options> list = loanApplyProductService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
