package com.buddha.controller.loan;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buddha.component.common.dto.Options;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.controller.WebBaseController;
import com.buddha.param.LoanMarketProductParam;
import com.buddha.pojo.loan.LoanMarketProduct;
import com.buddha.service.loan.LoanMarketProductService;

import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 贷款超市 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-08
 */
@RestController
@RequestMapping("loanMarketProduct")
@Log4j2
public class LoanMarketProductController extends WebBaseController {


    @Autowired
    private LoanMarketProductService loanMarketProductService;


    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListLoanMarketProduct(@RequestBody LoanMarketProductParam param){
        try {
        	QueryWrapper<LoanMarketProduct> queryWrapper = super.getQueryWrapper(LoanMarketProduct.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getTitle())) {
        		queryWrapper.like("title", param.getTitle());
        	}
        	if(StringUtils.isNotNull(param.getChargePerson())) {
        		queryWrapper.like("charge_person", param.getChargePerson());
        	}
        	if(StringUtils.isNotNull(param.getChargeMobile())) {
        		queryWrapper.like("charge_mobile", param.getChargeMobile());
        	}
        	if(StringUtils.isNotNull(param.getCompanyName())) {
        		queryWrapper.like("company_name", param.getCompanyName());
        	}
        	// 过滤数据
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<LoanMarketProduct> page = new Page<>(param.getPage(), param.getPageSize());
            page = loanMarketProductService.page(page, queryWrapper);
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
    public ResultJson listLoanMarketProduct(@RequestBody LoanMarketProductParam param){
        try {
        	QueryWrapper<LoanMarketProduct> queryWrapper = super.getQueryWrapper(LoanMarketProduct.class);
        	// [查询条件]根据业务补充...
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<LoanMarketProduct> list = loanMarketProductService.list(queryWrapper);
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
    public ResultJson detailLoanMarketProduct(@RequestBody LoanMarketProductParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            LoanMarketProduct detail = loanMarketProductService.getById(param.getId());
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
    public ResultJson saveLoanMarketProduct(@RequestBody LoanMarketProductParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	LoanMarketProduct loanMarketProduct = new LoanMarketProduct();
            BeanUtils.copyProperties(param, loanMarketProduct);
            loanMarketProduct.setCreateTime(curDate);
            loanMarketProduct.setUpdateTime(curDate);
            loanMarketProduct.setCreateId(super.systemAdminId.toString());
            loanMarketProduct.setAppId(super.appInfoId);
            loanMarketProductService.save(loanMarketProduct);
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
    public ResultJson updateLoanMarketProduct(@RequestBody LoanMarketProductParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	LoanMarketProduct loanMarketProduct = new LoanMarketProduct();
            BeanUtils.copyProperties(param, loanMarketProduct);
            loanMarketProduct.setUpdateTime(curDate);
            loanMarketProductService.updateById(loanMarketProduct);
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
    public ResultJson delLoanMarketProduct(@RequestBody LoanMarketProductParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            loanMarketProductService.removeById(param.getId());
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
	public ResultJson options(@RequestBody LoanMarketProductParam param) {
		try {
			if(StringUtils.isNull(param.getCreateId())) {
				param.setCreateId(super.systemAdminId.toString());
			}
			List<Options> list = loanMarketProductService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
