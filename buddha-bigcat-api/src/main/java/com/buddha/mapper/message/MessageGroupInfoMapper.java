package com.buddha.mapper.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.buddha.pojo.message.MessageGroupInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.buddha.component.common.dto.Options;
import com.buddha.param.MessageGroupInfoParam;

  /**
 * 
 * 群聊消息 Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface MessageGroupInfoMapper extends BaseMapper<MessageGroupInfo> {
 
	@Select("SELECT id AS 'key',name AS 'label' from message_group_info WHERE 1=1 AND is_del = 0 AND app_id = #{param.appId} ")
	List<Options> queryOptions(@Param("param") MessageGroupInfoParam param);
	
	
	/**
	 * 聊天记录
	 * @param param
	 * @return
	 */
	@ResultType(MessageGroupInfo.class)
	@Select("(SELECT message, url, type, from_id, create_time, 'mine' AS msgType\r\n" + 
			"         FROM message_group_info\r\n" + 
			"         WHERE 1 = 1\r\n" + 
			"           AND from_id = #{fromId}\r\n" + 
			"           AND to_id = #{toId})\r\n" + 
			"        UNION\r\n" + 
			"                (SELECT message, url, type, from_id, create_time, 'other' AS msgType\r\n" + 
			"                 FROM message_group_info\r\n" + 
			"                 WHERE 1 = 1\r\n" + 
			"                   AND from_id != #{fromId}\r\n" + 
			"                   AND to_id = #{toId})\r\n" + 
			"        ORDER BY create_time ASC")
	List<MessageGroupInfo> chatList(MessageGroupInfoParam param);
	
}