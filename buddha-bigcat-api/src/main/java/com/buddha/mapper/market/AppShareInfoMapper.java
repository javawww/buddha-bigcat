package com.buddha.mapper.market;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.buddha.pojo.market.AppShareInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.buddha.component.common.dto.Options;
import com.buddha.param.AppConfigInfoParam;
import com.buddha.param.AppShareInfoParam;

  /**
 * 
 * 共享App申请 Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface AppShareInfoMapper extends BaseMapper<AppShareInfo> {
 
	@Select("SELECT id AS 'key',name AS 'label' from app_share_info WHERE 1=1 AND is_del = 0 AND app_id = #{param.appId} ")
	List<Options> queryOptions(@Param("param") AppShareInfoParam param);

	@Select("SELECT app_id AS 'key',name AS 'label' from app_share_info WHERE 1=1 AND is_del = 0 AND create_id = #{param.createId} ")
	List<Options> queryShareOptions(@Param("param") AppConfigInfoParam param);
	
}