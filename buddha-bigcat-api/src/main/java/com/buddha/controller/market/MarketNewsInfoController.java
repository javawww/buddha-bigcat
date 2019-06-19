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
import com.buddha.service.market.MarketNewsInfoService;
import com.buddha.pojo.market.MarketNewsInfo;
import com.buddha.param.MarketNewsInfoParam;
import com.buddha.component.common.dto.Options;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 新闻内容 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-13
 */
@RestController
@RequestMapping("marketNewsInfo")
@Log4j2
public class MarketNewsInfoController extends WebBaseController {


    @Autowired
    private MarketNewsInfoService marketNewsInfoService;


    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListMarketNewsInfo(@RequestBody MarketNewsInfoParam param){
        try {
        	QueryWrapper<MarketNewsInfo> queryWrapper = super.getQueryWrapper(MarketNewsInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<MarketNewsInfo> page = new Page<MarketNewsInfo>(param.getPage(), param.getPageSize());
            page = marketNewsInfoService.page(page, queryWrapper);
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
    public ResultJson listMarketNewsInfo(@RequestBody MarketNewsInfoParam param){
        try {
        	QueryWrapper<MarketNewsInfo> queryWrapper = super.getQueryWrapper(MarketNewsInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	//排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<MarketNewsInfo> list = marketNewsInfoService.list(queryWrapper);
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
    public ResultJson detailMarketNewsInfo(@RequestBody MarketNewsInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            MarketNewsInfo detail = marketNewsInfoService.getById(param.getId());
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
    public ResultJson saveMarketNewsInfo(@RequestBody MarketNewsInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	MarketNewsInfo marketNewsInfo = new MarketNewsInfo();
            BeanUtils.copyProperties(param, marketNewsInfo);
            marketNewsInfo.setCreateTime(curDate);
            marketNewsInfo.setUpdateTime(curDate);
            marketNewsInfo.setCreateId(super.systemAdminId.toString());
            marketNewsInfo.setAppId(super.appInfoId);
            marketNewsInfoService.save(marketNewsInfo);
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
    public ResultJson updateMarketNewsInfo(@RequestBody MarketNewsInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	MarketNewsInfo marketNewsInfo = new MarketNewsInfo();
            BeanUtils.copyProperties(param, marketNewsInfo);
            marketNewsInfo.setUpdateTime(curDate);
            marketNewsInfoService.updateById(marketNewsInfo);
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
    public ResultJson delMarketNewsInfo(@RequestBody MarketNewsInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            marketNewsInfoService.removeById(param.getId());
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
	public ResultJson options(@RequestBody MarketNewsInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				param.setCreateId(super.appInfoId);
			}
			List<Options> list = marketNewsInfoService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
