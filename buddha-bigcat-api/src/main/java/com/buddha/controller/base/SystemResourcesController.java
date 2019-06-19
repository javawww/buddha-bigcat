package com.buddha.controller.base;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.controller.WebBaseController;
import com.buddha.param.SystemResourcesParam;
import com.buddha.pojo.base.SystemResources;
import com.buddha.service.base.SystemResourcesService;

import lombok.extern.log4j.Log4j2;
/**
 * 
 * @author chuck
 *
 */
@RestController
@RequestMapping("systemResources")
@Log4j2
public class SystemResourcesController extends WebBaseController {
	
	@Autowired
	private SystemResourcesService resourcesService;
	
	/**
	 * 保存资源
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveResources(@RequestBody SystemResourcesParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getPath())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "路径为空");
			}
			if(StringUtils.isNull(param.getComponent())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "组件为空");
			}
			if(StringUtils.isNull(param.getName())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "Name为空");
			}
			if(StringUtils.isNull(param.getTitle())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "标题为空");
			}
			if(StringUtils.isNull(param.getContent())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "说明为空");
			}
			Date curDate = new Date();
			// 保存
			SystemResources sysRes = new SystemResources();
			BeanUtils.copyProperties(param, sysRes);
			sysRes.setCreateId(super.systemAdminId.toString());
			sysRes.setCreateTime(curDate);
			sysRes.setUpdateTime(curDate);
			resourcesService.save(sysRes);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	/**
	 * 更新资源菜单
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	public ResultJson updateResources(@RequestBody SystemResourcesParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getId())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "主键为空");
			}
			if(StringUtils.isNull(param.getPath())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "路径为空");
			}
			if(StringUtils.isNull(param.getComponent())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "组件为空");
			}
			if(StringUtils.isNull(param.getName())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "Name为空");
			}
			if(StringUtils.isNull(param.getTitle())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "标题为空");
			}
			if(StringUtils.isNull(param.getContent())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "说明为空");
			}
			Date curDate = new Date();
			SystemResources sysRes = resourcesService.getById(param.getId());
			if(StringUtils.isNull(sysRes)) {
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST, "菜单资源不存在");
			}
			// 更新
			BeanUtils.copyProperties(param, sysRes);
			sysRes.setUpdateTime(curDate);
			resourcesService.updateById(sysRes);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	/**
	 * 删除资源
	 * @param param
	 * @return
	 */
	@PostMapping("del")
	public ResultJson delResources(@RequestBody SystemResourcesParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getId())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "主键为空");
			}else {
				//是否存在子节点
				QueryWrapper<SystemResources> queryWrapper = super.getQueryWrapper(SystemResources.class);
				queryWrapper.getEntity().setPid(Integer.valueOf(param.getId()));
				List<SystemResources> list = resourcesService.list(queryWrapper);
				if(StringUtils.isNotNull(list)) {
					return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "请先删除子节点");
				}
			}
			//删除
			resourcesService.removeById(param.getId());
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	/**
	 * 详情资源
	 * @param param
	 * @return
	 */
	@PostMapping("detail")
	public ResultJson detailResources(@RequestBody SystemResourcesParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getId())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "主键为空");
			}
			//查询
			SystemResources sysRes = resourcesService.getById(param.getId());
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,sysRes);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
