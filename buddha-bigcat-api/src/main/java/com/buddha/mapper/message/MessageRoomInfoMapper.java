package com.buddha.mapper.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.buddha.pojo.message.MessageRoomInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.buddha.component.common.dto.Options;
import com.buddha.param.MessageRoomInfoParam;

  /**
 * 
 * 聊天室 Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface MessageRoomInfoMapper extends BaseMapper<MessageRoomInfo> {
 
	@Select("SELECT id AS 'key',name AS 'label' from message_room_info WHERE 1=1 AND is_del = 0 AND app_id = #{param.appId} ")
	List<Options> queryOptions(@Param("param") MessageRoomInfoParam param);
	
}