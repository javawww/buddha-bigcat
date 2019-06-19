package com.buddha.controller.message;
import java.util.Date;
import java.util.List;

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
import com.buddha.service.message.MessageRoomInfoService;
import com.buddha.pojo.message.MessageRoomInfo;
import com.buddha.param.MessageRoomInfoParam;
import com.buddha.component.common.dto.Options;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 聊天室 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-16
 */
@RestController
@RequestMapping("messageRoomInfo")
@Log4j2
public class MessageRoomInfoController extends WebBaseController {


    @Autowired
    private MessageRoomInfoService messageRoomInfoService;


    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListMessageRoomInfo(@RequestBody MessageRoomInfoParam param){
        try {
        	QueryWrapper<MessageRoomInfo> queryWrapper = super.getQueryWrapper(MessageRoomInfo.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getMark())) {
        		queryWrapper.like("mark", param.getMark());
        	}
        	if(StringUtils.isNotNull(param.getName())) {
        		queryWrapper.like("name", param.getName());
        	}
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
//        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	// 排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<MessageRoomInfo> page = new Page<MessageRoomInfo>(param.getPage(), param.getPageSize());
            page = messageRoomInfoService.page(page, queryWrapper);
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
    public ResultJson listMessageRoomInfo(@RequestBody MessageRoomInfoParam param){
        try {
        	QueryWrapper<MessageRoomInfo> queryWrapper = super.getQueryWrapper(MessageRoomInfo.class);
        	// [查询条件]根据业务补充...
        	//queryWrapper.getEntity().setCreateId(super.systemAdminId.toString());
        	queryWrapper.getEntity().setAppId(super.appInfoId);
        	//排序
        	queryWrapper.orderByAsc("sorts");
        	queryWrapper.orderByDesc("create_time");
        	
            List<MessageRoomInfo> list = messageRoomInfoService.list(queryWrapper);
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
    public ResultJson detailMessageRoomInfo(@RequestBody MessageRoomInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            MessageRoomInfo detail = messageRoomInfoService.getById(param.getId());
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
    public ResultJson saveMessageRoomInfo(@RequestBody MessageRoomInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	MessageRoomInfo messageRoomInfo = new MessageRoomInfo();
            BeanUtils.copyProperties(param, messageRoomInfo);
            messageRoomInfo.setCreateTime(curDate);
            messageRoomInfo.setUpdateTime(curDate);
            messageRoomInfo.setCreateId(super.systemAdminId.toString());
            messageRoomInfo.setAppId(super.appInfoId);
            messageRoomInfoService.save(messageRoomInfo);
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
    public ResultJson updateMessageRoomInfo(@RequestBody MessageRoomInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	MessageRoomInfo messageRoomInfo = new MessageRoomInfo();
            BeanUtils.copyProperties(param, messageRoomInfo);
            messageRoomInfo.setUpdateTime(curDate);
            messageRoomInfoService.updateById(messageRoomInfo);
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
    public ResultJson delMessageRoomInfo(@RequestBody MessageRoomInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            messageRoomInfoService.removeById(param.getId());
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
	public ResultJson options(@RequestBody MessageRoomInfoParam param) {
		try {
			if(StringUtils.isNull(param.getAppId())) {
				param.setCreateId(super.appInfoId);
			}
			List<Options> list = messageRoomInfoService.queryOptions(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultJson(e);
		}
	}
}
