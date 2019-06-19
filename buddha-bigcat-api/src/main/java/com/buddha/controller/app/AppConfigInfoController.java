package com.buddha.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.enums.DelEnum;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.controller.AppBaseController;
import com.buddha.param.AppConfigInfoParam;
import com.buddha.pojo.loan.AppConfigInfo;
import com.buddha.pojo.loan.LoanMarketProduct;
import com.buddha.pojo.market.MarketCarouselInfo;
import com.buddha.pojo.market.MarketMarqueeInfo;
import com.buddha.service.loan.AppConfigInfoService;
import com.buddha.service.loan.LoanMarketProductService;
import com.buddha.service.market.MarketCarouselInfoService;
import com.buddha.service.market.MarketMarqueeInfoService;

import lombok.extern.log4j.Log4j2;
/**
 * APP端[应用管理]
 * @author chuck
 *
 */
@CrossOrigin(origins = {"http://192.168.8.178:9004","https://miraclewap.ywq157.com"}, maxAge = 3600)
@RestController("AppConfigInfoController")
@RequestMapping("app/appConfigInfoController")
@Log4j2
public class AppConfigInfoController extends AppBaseController{
	
	@Autowired
	private AppConfigInfoService appConfigInfoService;
	
	@Autowired
	private MarketCarouselInfoService marketCarouselInfoService;
	
	@Autowired
	private MarketMarqueeInfoService marketMarqueeInfoService; 
	
	@Autowired
	private LoanMarketProductService loanMarketProductService; 
	/**
	 * APP代号获取APP配置信息
	 * @param param
	 * @return
	 */
	@PostMapping("detailByAppCode")
	public ResultJson detailByAppCode(AppConfigInfoParam param) {
		try {
			if(StringUtils.isNull(super.appCode)) {
				log.error("APP_CODE为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_CODE为空");
			}
			QueryWrapper<AppConfigInfo> queryWrapper = super.getQueryWrapper(AppConfigInfo.class);
			queryWrapper.getEntity().setCodeName(super.appCode);
			queryWrapper.getEntity().setIsDel(DelEnum.NORMAL.getValue());
			AppConfigInfo appConfigInfo = appConfigInfoService.getOne(queryWrapper);
			if(StringUtils.isNull(appConfigInfo)) {
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST, "APP应用不存在");
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, appConfigInfo);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
	}
	
	/**
	 * 获取应用轮播图
	 * @return
	 */
	@PostMapping("carouseListByAppId")
	public ResultJson carouseListByAppId(AppConfigInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			QueryWrapper<MarketCarouselInfo> queryWrapper = super.getQueryWrapper(MarketCarouselInfo.class);
			queryWrapper.getEntity().setAppId(param.getAppId());
			queryWrapper.getEntity().setIsDel(DelEnum.NORMAL.getValue());
			queryWrapper.orderByAsc("sorts");
			List<MarketCarouselInfo> list = marketCarouselInfoService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
	}
	
	/**
	 * 获取应用跑马灯
	 * @return
	 */
	@PostMapping("marqueeListByAppId")
	public ResultJson marqueeListByAppId(AppConfigInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			QueryWrapper<MarketMarqueeInfo> queryWrapper = super.getQueryWrapper(MarketMarqueeInfo.class);
			queryWrapper.getEntity().setAppId(param.getAppId());
			queryWrapper.getEntity().setIsDel(DelEnum.NORMAL.getValue());
			queryWrapper.orderByAsc("sorts");
			List<MarketMarqueeInfo> list = marketMarqueeInfoService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
	}
	
	/**
	 * 本周放款榜
	 * @return
	 */
	@PostMapping("loanProductListTopWeek")
	public ResultJson loanProductListTopWeek(AppConfigInfoParam param) {
		try {
			if (StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			// 查询
			QueryWrapper<LoanMarketProduct> queryWrapper = super.getQueryWrapper(LoanMarketProduct.class);
			queryWrapper.getEntity().setAppId(param.getAppId());
			queryWrapper.getEntity().setIsDel(DelEnum.NORMAL.getValue());
			List<LoanMarketProduct> list = loanMarketProductService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 今日推荐
	 * @return
	 */
	@PostMapping("loanProductListToday")
	public ResultJson loanProductListToday(AppConfigInfoParam param) {
		try {
			if (StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			// 查询
			QueryWrapper<LoanMarketProduct> queryWrapper = super.getQueryWrapper(LoanMarketProduct.class);
			queryWrapper.getEntity().setAppId(param.getAppId());
			queryWrapper.getEntity().setIsDel(DelEnum.NORMAL.getValue());
			List<LoanMarketProduct> list = loanMarketProductService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 贷款大全
	 * @return
	 */
	@PostMapping("loanProductListAll")
	public ResultJson loanProductListAll(AppConfigInfoParam param) {
		try {
			if (StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			if(StringUtils.isNull(param.getSort())) {
				log.error("排序方式为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "排序方式为空");
			}
			// 查询
			QueryWrapper<LoanMarketProduct> queryWrapper = super.getQueryWrapper(LoanMarketProduct.class);
			queryWrapper.getEntity().setAppId(param.getAppId());
			queryWrapper.getEntity().setIsDel(DelEnum.NORMAL.getValue());
			// 综合排序
			if(Integer.valueOf(param.getSort())==0) {
				queryWrapper.orderByAsc("sorts");
			}
			// 额度高
			if(Integer.valueOf(param.getSort())==1) {
				queryWrapper.orderByDesc("high_limit");
			}
			// 下款快
			if(Integer.valueOf(param.getSort())==2) {
				queryWrapper.orderByDesc("month_rate");
			}
			// 通过率高
			if(Integer.valueOf(param.getSort())==3) {
				queryWrapper.orderByDesc("apply_amount");
			}
			List<LoanMarketProduct> list = loanMarketProductService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	/**
	 * 贷款详情
	 * @return
	 */
	@PostMapping("loanProductDetailById")
	public ResultJson loanProductDetailById(AppConfigInfoParam param) {
		try {
			if (StringUtils.isNull(param.getId())) {
				log.error("主键为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "主键为空");
			}
			if (StringUtils.isNull(param.getAppId())) {
				log.error("APP_ID为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "APP_ID为空");
			}
			// 查询
			LoanMarketProduct detail = loanMarketProductService.getById(param.getId());
			//修复图片宽度
			detail.setContent(detail.getContent().replaceAll("src=\"https://oss.ywq157.com", "width=\"100%\" src=\"https://oss.ywq157.com"));
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, detail);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
}
