package com.buddha.controller.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.dto.Options;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.controller.WebBaseController;
import com.buddha.param.SystemRolesParam;
import com.buddha.pojo.base.SystemResourcesRoles;
import com.buddha.pojo.base.SystemRoles;
import com.buddha.service.base.SystemResourcesRolesService;
import com.buddha.service.base.SystemRolesService;

import lombok.extern.log4j.Log4j2;
/**
 * 
 * @author chuck
 *
 */
@RestController
@RequestMapping("systemRoles")
@Log4j2
public class SystemRolesController extends WebBaseController{
	
	@Autowired
	private SystemRolesService systemRolesService;
	
	@Autowired
	private SystemResourcesRolesService resourcesRolesService;
	/**
	 * 角色列表
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	public ResultJson list(@RequestBody(required = false) SystemRolesParam param) {
		try {
			// 条件
			QueryWrapper<SystemRoles> queryWrapper = super.getQueryWrapper(SystemRoles.class);
			if(StringUtils.isNotNull(param.getRoleCode())) {
				queryWrapper.like("role_code", param.getRoleCode());
			}
			if(StringUtils.isNotNull(param.getRolesName())) {
				queryWrapper.like("roles_name", param.getRolesName());
				
			}
			List<SystemRoles> systemRoles = systemRolesService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, systemRoles);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	/**
	 * 角色拥有的菜单资源id数组
	 * @param param
	 * @return
	 */
	@PostMapping("checkedResArr")
	public ResultJson checkedResArr(@RequestBody SystemRolesParam param) {
		try {
			//判断
			if(StringUtils.isNull(param.getId())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "角色id为空");
			}
			// 查询
			Integer[] checkedResArr =  systemRolesService.checkedResArr(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, checkedResArr);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	/**
	 * 新增角色
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveSystemRoles(@RequestBody SystemRolesParam param) {
		try {
			//判断
			if(StringUtils.isNull(param.getRoleCode())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "角色编码为空");
			}else {
				QueryWrapper<SystemRoles> queryWrapper = super.getQueryWrapper(SystemRoles.class);
				queryWrapper.getEntity().setRoleCode(param.getRoleCode());
				List<SystemRoles> list = systemRolesService.list(queryWrapper);
				if(StringUtils.isNotNull(list)) {
					return new ResultJson(ResultStatusEnum.COMMON_FAIL, "角色编码已存在");
				}
			}
			if(StringUtils.isNull(param.getRolesName())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "角色名称为空");
			}
			if(StringUtils.isNull(param.getContent())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "角色描述为空");
			}
			Date curDate = new Date();
			//保存角色
			SystemRoles sysRoles = new SystemRoles();
			BeanUtils.copyProperties(param, sysRoles);
			sysRoles.setCreateTime(curDate);
			sysRoles.setUpdateTime(curDate);
			sysRoles.setUserId(super.systemAdminId);
			systemRolesService.save(sysRoles);
			// 管理菜单权限
			if(StringUtils.isNotNull(param.getResourcesIdArr())) {
				List<SystemResourcesRoles> systemResourcesRoles = new ArrayList<SystemResourcesRoles>();
				for (Integer resid : param.getResourcesIdArr()) {
					SystemResourcesRoles resourcesRoles = new SystemResourcesRoles();
					resourcesRoles.setRolesId(sysRoles.getId());
					resourcesRoles.setResourcesId(resid);
					resourcesRoles.setCreateTime(curDate);
					systemResourcesRoles.add(resourcesRoles);
				}
				resourcesRolesService.saveBatch(systemResourcesRoles);
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	/**
	 * 更新角色
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	public ResultJson updateSystemRoles(@RequestBody SystemRolesParam param) {
		try {
			//判断
			if(StringUtils.isNull(param.getId())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "主键为空");
			}
			if(StringUtils.isNull(param.getRoleCode())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "角色编码为空");
			}
			if(StringUtils.isNull(param.getRolesName())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "角色名称为空");
			}
			if(StringUtils.isNull(param.getContent())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "角色描述为空");
			}
			Date curDate = new Date();
			//保存角色
			SystemRoles sysRoles = new SystemRoles();
			BeanUtils.copyProperties(param, sysRoles);
			sysRoles.setId(Integer.valueOf(param.getId()));
			sysRoles.setUpdateTime(curDate);
			systemRolesService.updateById(sysRoles);
			// 管理菜单权限
			if(StringUtils.isNotNull(param.getResourcesIdArr())) {
				// 清空此角色关联的所有菜单
				QueryWrapper<SystemResourcesRoles> queryWrapper = super.getQueryWrapper(SystemResourcesRoles.class);
				queryWrapper.getEntity().setRolesId(sysRoles.getId());
				resourcesRolesService.remove(queryWrapper);
				// 重新绑定关联关系
				List<SystemResourcesRoles> systemResourcesRoles = new ArrayList<SystemResourcesRoles>();
				for (Integer resid : param.getResourcesIdArr()) {
					SystemResourcesRoles resourcesRoles = new SystemResourcesRoles();
					resourcesRoles.setRolesId(sysRoles.getId());
					resourcesRoles.setResourcesId(resid);
					resourcesRoles.setCreateTime(curDate);
					systemResourcesRoles.add(resourcesRoles);
				}
				resourcesRolesService.saveBatch(systemResourcesRoles);
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	/**
	 * 更新角色
	 * @param param
	 * @return
	 */
	@PostMapping("del")
	public ResultJson delSystemRoles(@RequestBody SystemRolesParam param) {
		try {
			//判断
			if(StringUtils.isNull(param.getId())) {
				return new ResultJson(ResultStatusEnum.COMMON_FAIL, "主键为空");
			}
			// 删除角色
			systemRolesService.removeById(param.getId());
			// 删除角色关联的资源
			QueryWrapper<SystemResourcesRoles> queryWrapper = super.getQueryWrapper(SystemResourcesRoles.class);
			queryWrapper.getEntity().setRolesId(Integer.valueOf(param.getId()));
			resourcesRolesService.remove(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	/**
	 * 下拉选项
	 * @param param
	 * @return
	 */
	@PostMapping("options")
	public ResultJson options(@RequestBody SystemRolesParam param) {
		try {
			if(StringUtils.isNull(param.getCreateId())) {
				param.setCreateId(super.systemAdminId.toString());
			}
			List<Options> list = systemRolesService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
