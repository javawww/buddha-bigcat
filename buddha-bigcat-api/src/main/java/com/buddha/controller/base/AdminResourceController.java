package com.buddha.controller.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.dto.LoginAdminBean;
import com.buddha.component.common.enums.DelEnum;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import com.buddha.controller.WebBaseController;
import com.buddha.param.BaseParam;
import com.buddha.pojo.base.Meta;
import com.buddha.pojo.base.SystemAdmin;
import com.buddha.pojo.base.SystemResources;
import com.buddha.pojo.base.SystemResourcesRoles;
import com.buddha.service.base.SystemAdminService;
import com.buddha.service.base.SystemResourcesRolesService;
import com.buddha.service.base.SystemResourcesService;

import lombok.extern.log4j.Log4j2;

/**
 * 菜单路由
 * @author chuck
 *
 */
@RestController
@RequestMapping("admin-resource")
@Log4j2
public class AdminResourceController extends WebBaseController{
	
	@Autowired
	private SystemResourcesService resourcesService;
	@Autowired
    private SystemResourcesRolesService resourcesRolesService;
	
	private List<Integer>                resRoleIds = new ArrayList<Integer>();
    private List<SystemResources>        allres;
    private byte[]                       mtoken      = new byte[0];
    
    /**
	 * 返回菜单列表[无权限控制]
	 * @param token
	 * @return
	 */
    @PostMapping("resTree")
    public ResultJson resTree() {
		try {
			// 获取所有菜单资源
			QueryWrapper<SystemResources> queryWrapper = super.getQueryWrapper(SystemResources.class);
			queryWrapper.getEntity().setIsDel(DelEnum.NORMAL.getValue());
			queryWrapper.orderByAsc("sorts");
			List<SystemResources>  srrlist = resourcesService.list(queryWrapper);
			this.resRoleIds.clear();
			for (SystemResources sr : srrlist) {
				this.resRoleIds.add(sr.getId());
			}
			// 获取菜单列表
			List<SystemResources> tree = new ArrayList<SystemResources>();
			synchronized (mtoken) {
				this.allres = resourcesService.list();
				QueryWrapper<SystemResources> queryWrapper3 = super.getQueryWrapper(SystemResources.class);
				queryWrapper3.isNull("pid");
				queryWrapper3.orderByAsc("sorts");
				generateTree(tree, resourcesService.list(queryWrapper3));
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, tree);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
    
	/**
	 * 返回菜单列表[权限控制]
	 * @param token
	 * @return
	 */
	@PostMapping("roleResTree")
	public ResultJson roleResTree() {
		try {
			if(StringUtils.isNull(super.loginAdminBean.getSysAdmin().getRoleId())) {
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "角色不存在");
			}
			// 通过角色ID获取关联的菜单资源
			QueryWrapper<SystemResourcesRoles> queryWrapper = super.getQueryWrapper(SystemResourcesRoles.class);
			queryWrapper.getEntity().setRolesId(super.loginAdminBean.getSysAdmin().getRoleId());
			List<SystemResourcesRoles>  srrlist = resourcesRolesService.list(queryWrapper);
			this.resRoleIds.clear();
			for (SystemResourcesRoles sr : srrlist) {
				this.resRoleIds.add(sr.getResourcesId());
			}
			// 获取菜单列表
			List<SystemResources> tree = new ArrayList<SystemResources>();
			synchronized (mtoken) {
//				QueryWrapper<SystemResources> queryWrapper2 = super.getQueryWrapper(SystemResources.class);
				this.allres = resourcesService.list();
				QueryWrapper<SystemResources> queryWrapper3 = super.getQueryWrapper(SystemResources.class);
				queryWrapper3.isNull("pid");
				queryWrapper3.orderByAsc("sorts");
				generateTree(tree, resourcesService.list(queryWrapper3));
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, tree);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	
	/**
     * 递归生成树
     * @param treelist
     * @param data
     * @return
     */
    private List<SystemResources> generateTree(List<SystemResources> treelist, List<SystemResources> data) {
    	for (SystemResources sr : data) {
    		SystemResources tree = new SystemResources();
    		Meta meta = new Meta();
    		if(StringUtils.isNotNull(sr.getRolesDb())) {
//    			meta.setRoles(sr.getRolesDb().split("|"));
    			sr.setRoles(StringUtils.string2List(sr.getRolesDb()));
    		}
    		meta.setTitle(sr.getTitle());
    		meta.setIcon(sr.getIcon());
    		if(0==sr.getNoCache()) {
    			meta.setNoCache(false);
    		}else {
    			meta.setNoCache(true);
    		}
    		if(0==sr.getAffix()) {
    			meta.setAffix(false);
    		}else {
    			meta.setAffix(true);
    		}
    		if(0==sr.getBreadcrumb()) {
    			meta.setBreadcrumb(false);
    		}else {
    			meta.setBreadcrumb(true);
    		}
    		meta.setActiveMenu(sr.getActiveMenu());
    		sr.setMeta(meta);
//    		tree.setId(sr.getId());
    		BeanUtils.copyProperties(sr, tree);
    		if (this.resRoleIds != null && this.resRoleIds.contains(sr.getId())) {
    			// 选中状态
    			tree.setChildren(generateTree(new ArrayList<SystemResources>(), getByPid(sr.getId())));
    			treelist.add(tree);
    		}
    	}
    	return treelist;
    }
    
    private List<SystemResources> getByPid(Integer pid) {
        if (this.allres == null || this.allres.size() < 1)
            return null;
        List<SystemResources> reslist = new ArrayList<SystemResources>();
        for (SystemResources res : this.allres) {
            if (null != res.getPid() && res.getPid().intValue() == pid.intValue())
                reslist.add(res);
        }
        return reslist;
    }
}
