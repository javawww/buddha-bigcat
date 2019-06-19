package com.buddha.controller.base;
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
import com.buddha.service.base.SystemAdminService;
import com.buddha.service.base.SystemRolesService;
import com.buddha.pojo.base.SystemAdmin;
import com.buddha.pojo.base.SystemRoles;
import com.buddha.param.SystemAdminParam;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.Md5Util;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 系统管理员表 前端控制器
 * </p>
 *
 * @author 蚂蚁社区
 * @since 2019-06-08
 */
@RestController
@RequestMapping("systemAdmin")
@Log4j2
public class SystemAdminController extends WebBaseController {


    @Autowired
    private SystemAdminService systemAdminService;
    
    @Autowired
    private SystemRolesService systemRolesService;

    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListSystemAdmin(@RequestBody SystemAdminParam param){
        try {
        	QueryWrapper<SystemAdmin> queryWrapper = super.getQueryWrapper(SystemAdmin.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getName())) {
        		queryWrapper.like("name", param.getName());
        	}
        	if(StringUtils.isNotNull(param.getRoleCode())) {
        		queryWrapper.like("role_code", param.getRoleCode());
        	}
        	if(StringUtils.isNotNull(param.getRoleName())) {
        		queryWrapper.like("role_name", param.getRoleName());
        	}
        	if(StringUtils.isNotNull(param.getNickname())) {
        		queryWrapper.like("nickname", param.getNickname());
        	}
        	if(StringUtils.isNotNull(param.getMobile())) {
        		queryWrapper.like("mobile", param.getMobile());
        	}
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<SystemAdmin> page = new Page<>(param.getPage(), param.getPageSize());
            page = systemAdminService.page(page, queryWrapper);
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
    public ResultJson listSystemAdmin(@RequestBody SystemAdminParam param){
        try {
        	QueryWrapper<SystemAdmin> queryWrapper = super.getQueryWrapper(SystemAdmin.class);
        	// [查询条件]根据业务补充...
        	queryWrapper.orderByDesc("create_time");
        	
            List<SystemAdmin> list = systemAdminService.list(queryWrapper);
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
    public ResultJson detailSystemAdmin(@RequestBody SystemAdminParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            SystemAdmin detail = systemAdminService.getById(param.getId());
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
    public ResultJson saveSystemAdmin(@RequestBody SystemAdminParam param){
        try {
            // [判断条件]根据业务补充...
        	if(StringUtils.isNull(param.getPassword())) {
        		param.setPassword(Md5Util.getMD5String("123456")); // 初始化登录密码
        	}
        	if(StringUtils.isNull(param.getBalancePwd())) {
        		param.setBalancePwd(Md5Util.getMD5String("123456")); // 余额支付密码
        	}
        	// 角色设置
        	if(StringUtils.isNotNull(param.getRoleId())) {
        		SystemRoles roles =  systemRolesService.getById(param.getRoleId());
        		param.setRoleId(roles.getId());
        		param.setRoleCode(roles.getRoleCode());
        		param.setRoleName(roles.getRolesName());
        	}
        	Date curDate = new Date();
        	SystemAdmin systemAdmin = new SystemAdmin();
            BeanUtils.copyProperties(param, systemAdmin);
            systemAdmin.setCreateTime(curDate);
            systemAdmin.setUpdateTime(curDate);
            systemAdminService.save(systemAdmin);
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
    public ResultJson updateSystemAdmin(@RequestBody SystemAdminParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            // 角色设置
        	if(StringUtils.isNotNull(param.getRoleId())) {
        		SystemRoles roles =  systemRolesService.getById(param.getRoleId());
        		param.setRoleId(roles.getId());
        		param.setRoleCode(roles.getRoleCode());
        		param.setRoleName(roles.getRolesName());
        	}
        	Date curDate = new Date();
        	SystemAdmin systemAdmin = new SystemAdmin();
        	systemAdmin.setId(Integer.valueOf(param.getId()));
            BeanUtils.copyProperties(param, systemAdmin);
            systemAdmin.setUpdateTime(curDate);
            systemAdminService.updateById(systemAdmin);
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
    public ResultJson delSystemAdmin(@RequestBody SystemAdminParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            systemAdminService.removeById(param.getId());
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
}
