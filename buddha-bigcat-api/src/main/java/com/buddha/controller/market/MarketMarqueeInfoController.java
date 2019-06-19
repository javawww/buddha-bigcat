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
import com.buddha.service.market.MarketMarqueeInfoService;
import com.buddha.pojo.market.MarketMarqueeInfo;
import com.buddha.param.MarketMarqueeInfoParam;
import com.buddha.component.common.dto.Options;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 跑马灯 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-13
 */
@RestController
@RequestMapping("marketMarqueeInfo")
@Log4j2
public class MarketMarqueeInfoController extends WebBaseController {


    @Autowired
    private MarketMarqueeInfoService marketMarqueeInfoService;


    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListMarketMarqueeInfo(@RequestBody MarketMarqueeInfoParam param){
        try {
        	QueryWrapper<MarketMarqueeInfo> queryWrapper = super.getQueryWrapper(MarketMarqueeInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<MarketMarqueeInfo> page = new Page<MarketMarqueeInfo>(param.getPage(), param.getPageSize());
            page = marketMarqueeInfoService.page(page, queryWrapper);
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
    public ResultJson listMarketMarqueeInfo(@RequestBody MarketMarqueeInfoParam param){
        try {
        	QueryWrapper<MarketMarqueeInfo> queryWrapper = super.getQueryWrapper(MarketMarqueeInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	//排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<MarketMarqueeInfo> list = marketMarqueeInfoService.list(queryWrapper);
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
    public ResultJson detailMarketMarqueeInfo(@RequestBody MarketMarqueeInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            MarketMarqueeInfo detail = marketMarqueeInfoService.getById(param.getId());
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
    public ResultJson saveMarketMarqueeInfo(@RequestBody MarketMarqueeInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	MarketMarqueeInfo marketMarqueeInfo = new MarketMarqueeInfo();
            BeanUtils.copyProperties(param, marketMarqueeInfo);
            marketMarqueeInfo.setCreateTime(curDate);
            marketMarqueeInfo.setUpdateTime(curDate);
            marketMarqueeInfo.setCreateId(super.systemAdminId.toString());
            marketMarqueeInfo.setAppId(super.appInfoId);
            marketMarqueeInfoService.save(marketMarqueeInfo);
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
    public ResultJson updateMarketMarqueeInfo(@RequestBody MarketMarqueeInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	MarketMarqueeInfo marketMarqueeInfo = new MarketMarqueeInfo();
            BeanUtils.copyProperties(param, marketMarqueeInfo);
            marketMarqueeInfo.setUpdateTime(curDate);
            marketMarqueeInfoService.updateById(marketMarqueeInfo);
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
    public ResultJson delMarketMarqueeInfo(@RequestBody MarketMarqueeInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            marketMarqueeInfoService.removeById(param.getId());
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
	public ResultJson options(@RequestBody MarketMarqueeInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				param.setCreateId(super.appInfoId);
			}
			List<Options> list = marketMarqueeInfoService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
