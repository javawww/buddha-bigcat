package com.buddha.mapper.loan;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buddha.component.common.dto.Options;
import com.buddha.param.AppConfigInfoParam;
import com.buddha.pojo.loan.AppConfigInfo;

  /**
 * 
 * APP设置 Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface AppConfigInfoMapper extends BaseMapper<AppConfigInfo> {
	
	@Select("SELECT id AS 'key',name AS 'label' from app_config_info WHERE 1=1 AND is_del = 0 AND create_id = #{param.createId} ")
	List<Options> queryOptions(@Param("param") AppConfigInfoParam param);

}