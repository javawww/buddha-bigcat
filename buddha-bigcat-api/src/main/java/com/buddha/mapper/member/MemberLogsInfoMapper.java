package com.buddha.mapper.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.buddha.pojo.member.MemberLogsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.buddha.component.common.dto.Options;
import com.buddha.param.MemberLogsInfoParam;

  /**
 * 
 * 会员日志记录 Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface MemberLogsInfoMapper extends BaseMapper<MemberLogsInfo> {
 
	@Select("SELECT id AS 'key',name AS 'label' from member_logs_info WHERE 1=1 AND is_del = 0 AND app_id = #{param.appId} ")
	List<Options> queryOptions(@Param("param") MemberLogsInfoParam param);
	
}