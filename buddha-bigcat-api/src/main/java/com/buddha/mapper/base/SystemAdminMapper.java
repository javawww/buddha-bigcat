package com.buddha.mapper.base;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buddha.pojo.base.SystemAdmin;


  /**
 * 
 * 系统管理员表 Mapper 接口
 *
 */
@DS("bigcat_base")
public interface SystemAdminMapper extends BaseMapper<SystemAdmin> {

}