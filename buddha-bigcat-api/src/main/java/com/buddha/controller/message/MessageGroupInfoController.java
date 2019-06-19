package com.buddha.controller.message;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
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
import com.buddha.service.message.MessageGroupInfoService;
import com.buddha.pojo.base.SystemAdmin;
import com.buddha.pojo.message.MessageGroupInfo;
import com.buddha.param.MessageGroupInfoParam;
import com.buddha.component.common.constant.RedisKeyConstant;
import com.buddha.component.common.dto.Options;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 群聊消息 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-17
 */
@RestController
@RequestMapping("messageGroupInfo")
@Log4j2
public class MessageGroupInfoController extends WebBaseController {


    @Autowired
    private MessageGroupInfoService messageGroupInfoService;
    
    @Autowired
    private SystemAdminService systemAdminService;
    
    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListMessageGroupInfo(@RequestBody MessageGroupInfoParam param){
        try {
        	QueryWrapper<MessageGroupInfo> queryWrapper = super.getQueryWrapper(MessageGroupInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<MessageGroupInfo> page = new Page<MessageGroupInfo>(param.getPage(), param.getPageSize());
            page = messageGroupInfoService.page(page, queryWrapper);
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, page);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }


    /**
     * 全部数据
     */
    @PostMapping("list")
    public ResultJson listMessageGroupInfo(@RequestBody MessageGroupInfoParam param){
        try {
        	QueryWrapper<MessageGroupInfo> queryWrapper = super.getQueryWrapper(MessageGroupInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	//排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<MessageGroupInfo> list = messageGroupInfoService.list(queryWrapper);
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
    public ResultJson detailMessageGroupInfo(@RequestBody MessageGroupInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            MessageGroupInfo detail = messageGroupInfoService.getById(param.getId());
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
    public ResultJson saveMessageGroupInfo(@RequestBody MessageGroupInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	MessageGroupInfo messageGroupInfo = new MessageGroupInfo();
            BeanUtils.copyProperties(param, messageGroupInfo);
            messageGroupInfo.setCreateTime(curDate);
            messageGroupInfo.setUpdateTime(curDate);
            messageGroupInfo.setCreateId(super.systemAdminId.toString());
            messageGroupInfo.setAppId(super.appInfoId);
            messageGroupInfoService.save(messageGroupInfo);
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
    public ResultJson updateMessageGroupInfo(@RequestBody MessageGroupInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	MessageGroupInfo messageGroupInfo = new MessageGroupInfo();
            BeanUtils.copyProperties(param, messageGroupInfo);
            messageGroupInfo.setUpdateTime(curDate);
            messageGroupInfoService.updateById(messageGroupInfo);
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
    public ResultJson delMessageGroupInfo(@RequestBody MessageGroupInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            messageGroupInfoService.removeById(param.getId());
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
    
    /**
	 * 下拉选项
	 */
	@PostMapping("options")
	public ResultJson options(@RequestBody MessageGroupInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				param.setCreateId(super.appInfoId);
			}
			List<Options> list = messageGroupInfoService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
	
	 /**
     * 记录用户进入聊天室
     *
     * @param param
     * @return
     */
    @PostMapping("roomUserAdd")
    public ResultJson roomUserAdd(@RequestBody MessageGroupInfoParam param) {
    	try {
    		// 判断
            if (StringUtils.isNull(param.getRoomId())) {
                log.info("聊天室id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "聊天室id为空");
            }
            if (StringUtils.isNull(param.getFromId())) {
                log.info("用户id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "用户id为空");
            }
            //获取当前登录会员
            SystemAdmin systemAdmin = systemAdminService.getById(param.getFromId());
            redisService.set(RedisKeyConstant.ROOM_KEY_HEAD + param.getRoomId() + ":" + param.getFromId(), JSONObject.toJSONString(systemAdmin));
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
    }
    
    /**
     * 删除聊天室用户
     *
     * @param param
     * @return
     */
    @PostMapping("roomUserDelete")
    public ResultJson roomUserDelete(@RequestBody MessageGroupInfoParam param) {
    	try {
    		// 判断
            if (StringUtils.isNull(param.getRoomId())) {
                log.info("聊天室id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "聊天室id为空");
            }
            if (StringUtils.isNull(param.getFromId())) {
                log.info("用户id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "用户id为空");
            }
            // 移除
            redisService.delKeysLike(RedisKeyConstant.ROOM_KEY_HEAD + param.getRoomId() + ":" + param.getFromId());;
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
    }
    
    /**
     * 获取聊天室用户列表
     *
     * @param param
     * @return
     */
    @PostMapping("roomUserList")
    public ResultJson roomUserList(@RequestBody MessageGroupInfoParam param) {
    	try {
    		// 判断
            if (StringUtils.isNull(param.getRoomId())) {
                log.info("聊天室id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "聊天室id为空");
            }
            // 列表数组
            List<SystemAdmin> systemAdmins = new ArrayList<SystemAdmin>();
            // 遍历房间所有key
            String likeKey = RedisKeyConstant.ROOM_KEY_HEAD + param.getRoomId() + ":*";
            Set<String> keys = redisService.getKeysLike(likeKey);
            Iterator<String> it = keys.iterator();
            while(it.hasNext()) {
            	String keyStr = it.next();
            	SystemAdmin systemAdmin = redisService.get(keyStr, SystemAdmin.class);
            	systemAdmins.add(systemAdmin);
            }
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,systemAdmins);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
    }
    
    /**
     * 聊天记录列表
     *
     * @param param
     * @return
     */
    @PostMapping("chatList")
    public ResultJson chatList(@RequestBody MessageGroupInfoParam param) {
    	try {
            // 判断
            if (StringUtils.isNull(param.getFromId())) {
                log.info("发送者为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "发送者为空");
            }
            if (StringUtils.isNull(param.getToId())) {
                log.info("接受者为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "接受者为空");
            }
            // 查询聊天记录
            List<MessageGroupInfo> msgList = messageGroupInfoService.chatList(param);
            if (StringUtils.isNotNull(msgList)) {
                for (MessageGroupInfo msg : msgList) {
                	msg.setDatetime(msg.getCreateTime());
                	msg.setMsg(msg.getMessage());
                    SystemAdmin systemAdmin = systemAdminService.getById(msg.getFromId());
                    if (StringUtils.isNotNull(systemAdmin)) {
                         msg.setUser(systemAdmin);
                    }
                }
            }
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, msgList);
        } catch (Exception e) {
            log.error("系统异常，请检查", e);
            return new ResultJson(e);
        }
    }
}
