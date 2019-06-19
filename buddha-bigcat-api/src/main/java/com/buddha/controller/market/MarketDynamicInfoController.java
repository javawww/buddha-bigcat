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
import com.buddha.service.market.MarketDynamicInfoService;
import com.buddha.pojo.market.MarketDynamicInfo;
import com.buddha.param.MarketDynamicInfoParam;
import com.buddha.component.common.dto.Options;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 动态内容 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-13
 */
@RestController
@RequestMapping("marketDynamicInfo")
@Log4j2
public class MarketDynamicInfoController extends WebBaseController {


    @Autowired
    private MarketDynamicInfoService marketDynamicInfoService;


    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListMarketDynamicInfo(@RequestBody MarketDynamicInfoParam param){
        try {
        	QueryWrapper<MarketDynamicInfo> queryWrapper = super.getQueryWrapper(MarketDynamicInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<MarketDynamicInfo> page = new Page<MarketDynamicInfo>(param.getPage(), param.getPageSize());
            page = marketDynamicInfoService.page(page, queryWrapper);
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
    public ResultJson listMarketDynamicInfo(@RequestBody MarketDynamicInfoParam param){
        try {
        	QueryWrapper<MarketDynamicInfo> queryWrapper = super.getQueryWrapper(MarketDynamicInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	//排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<MarketDynamicInfo> list = marketDynamicInfoService.list(queryWrapper);
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
    public ResultJson detailMarketDynamicInfo(@RequestBody MarketDynamicInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            MarketDynamicInfo detail = marketDynamicInfoService.getById(param.getId());
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
    public ResultJson saveMarketDynamicInfo(@RequestBody MarketDynamicInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	MarketDynamicInfo marketDynamicInfo = new MarketDynamicInfo();
            BeanUtils.copyProperties(param, marketDynamicInfo);
            marketDynamicInfo.setCreateTime(curDate);
            marketDynamicInfo.setUpdateTime(curDate);
            marketDynamicInfo.setCreateId(super.systemAdminId.toString());
            marketDynamicInfo.setAppId(super.appInfoId);
            marketDynamicInfoService.save(marketDynamicInfo);
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
    public ResultJson updateMarketDynamicInfo(@RequestBody MarketDynamicInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	MarketDynamicInfo marketDynamicInfo = new MarketDynamicInfo();
            BeanUtils.copyProperties(param, marketDynamicInfo);
            marketDynamicInfo.setUpdateTime(curDate);
            marketDynamicInfoService.updateById(marketDynamicInfo);
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
    public ResultJson delMarketDynamicInfo(@RequestBody MarketDynamicInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            marketDynamicInfoService.removeById(param.getId());
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
	public ResultJson options(@RequestBody MarketDynamicInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				param.setCreateId(super.appInfoId);
			}
			List<Options> list = marketDynamicInfoService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
