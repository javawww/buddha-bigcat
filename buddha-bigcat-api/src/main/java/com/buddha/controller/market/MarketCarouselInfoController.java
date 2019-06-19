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
import com.buddha.service.market.MarketCarouselInfoService;
import com.buddha.pojo.market.MarketCarouselInfo;
import com.buddha.param.MarketCarouselInfoParam;
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
 * @since 2019-06-13
 */
@RestController
@RequestMapping("marketCarouselInfo")
@Log4j2
public class MarketCarouselInfoController extends WebBaseController {


    @Autowired
    private MarketCarouselInfoService marketCarouselInfoService;


    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListMarketCarouselInfo(@RequestBody MarketCarouselInfoParam param){
        try {
        	QueryWrapper<MarketCarouselInfo> queryWrapper = super.getQueryWrapper(MarketCarouselInfo.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getName())) {
        		queryWrapper.like("name", param.getName());
        	}
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<MarketCarouselInfo> page = new Page<MarketCarouselInfo>(param.getPage(), param.getPageSize());
            page = marketCarouselInfoService.page(page, queryWrapper);
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
    public ResultJson listMarketCarouselInfo(@RequestBody MarketCarouselInfoParam param){
        try {
        	QueryWrapper<MarketCarouselInfo> queryWrapper = super.getQueryWrapper(MarketCarouselInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	//排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<MarketCarouselInfo> list = marketCarouselInfoService.list(queryWrapper);
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
    public ResultJson detailMarketCarouselInfo(@RequestBody MarketCarouselInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            MarketCarouselInfo detail = marketCarouselInfoService.getById(param.getId());
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
    public ResultJson saveMarketCarouselInfo(@RequestBody MarketCarouselInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	MarketCarouselInfo marketCarouselInfo = new MarketCarouselInfo();
            BeanUtils.copyProperties(param, marketCarouselInfo);
            marketCarouselInfo.setCreateTime(curDate);
            marketCarouselInfo.setUpdateTime(curDate);
            marketCarouselInfo.setCreateId(super.systemAdminId.toString());
            marketCarouselInfo.setAppId(super.appInfoId);
            marketCarouselInfoService.save(marketCarouselInfo);
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
    public ResultJson updateMarketCarouselInfo(@RequestBody MarketCarouselInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	MarketCarouselInfo marketCarouselInfo = new MarketCarouselInfo();
            BeanUtils.copyProperties(param, marketCarouselInfo);
            marketCarouselInfo.setUpdateTime(curDate);
            marketCarouselInfoService.updateById(marketCarouselInfo);
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
    public ResultJson delMarketCarouselInfo(@RequestBody MarketCarouselInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            marketCarouselInfoService.removeById(param.getId());
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
	public ResultJson options(@RequestBody MarketCarouselInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				param.setCreateId(super.appInfoId);
			}
			List<Options> list = marketCarouselInfoService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
