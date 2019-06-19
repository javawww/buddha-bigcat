package com.buddha.mapper.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.buddha.pojo.member.MemberInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.buddha.component.common.dto.Options;
import com.buddha.param.MemberInfoParam;

  /**
 * 
 * 会员基本信息 Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface MemberInfoMapper extends BaseMapper<MemberInfo> {
 
	@Select("SELECT id AS 'key',name AS 'label' from member_info WHERE 1=1 AND is_del = 0 AND app_id = #{param.appId} ")
	List<Options> queryOptions(@Param("param") MemberInfoParam param);
	
}