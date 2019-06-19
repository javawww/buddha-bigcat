package com.buddha.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buddha.component.common.dto.Options;
import com.buddha.param.SystemRolesParam;
import com.buddha.pojo.base.SystemRoles;


  /**
 * 
 * 角色表 Mapper 接口
 *
 */
@DS("bigcat_base")
public interface SystemRolesMapper extends BaseMapper<SystemRoles> {
	
	@Select("SELECT resources_id from system_resources_roles WHERE 1=1 AND roles_id = #{param.id} ")
	Integer[] checkedResArr(@Param("param") SystemRolesParam param);

	
	@Select("SELECT id AS 'key',roles_name AS 'label' from system_roles WHERE 1=1 AND is_del = 0 AND user_id = #{param.createId}")
	List<Options> queryOptions(@Param("param") SystemRolesParam param);

}