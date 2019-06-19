package com.buddha.controller.topic;
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
import com.buddha.service.topic.TopicInfoService;
import com.buddha.pojo.topic.TopicInfo;
import com.buddha.param.TopicInfoParam;

import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.utils.ResultJson;
import com.buddha.component.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 主题话题 前端控制器
 * </p>
 *
 * @author bigcat
 * @since 2019-06-06
 */
@RestController
@RequestMapping("topicInfo")
@Log4j2
public class TopicInfoController extends WebBaseController {


    @Autowired
    private TopicInfoService topicInfoService;


    /**
	  * 分页
	 */
	@PostMapping("pageList")
    public ResultJson pageListTopicInfo(@RequestBody TopicInfoParam param){
        try {
        	QueryWrapper<TopicInfo> queryWrapper = super.getQueryWrapper(TopicInfo.class);
        	// [查询条件]根据业务补充...
        	if(StringUtils.isNotNull(param.getTopic())) {
        		queryWrapper.like("topic", param.getTopic());
        	}
        	queryWrapper.orderByDesc("create_time");
        	
        	IPage<TopicInfo> page = new Page<>(param.getPage(), param.getPageSize());
            page = topicInfoService.page(page, queryWrapper);
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, page);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }


    /**
        *  全部数据
     */
    @PostMapping("list")
    public ResultJson listTopicInfo(@RequestBody TopicInfoParam param){
        try {
        	QueryWrapper<TopicInfo> queryWrapper = super.getQueryWrapper(TopicInfo.class);
        	// [查询条件]根据业务补充...
        	queryWrapper.orderByDesc("create_time");
        	
            List<TopicInfo> list = topicInfoService.list(queryWrapper);
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
    public ResultJson detailTopicInfo(@RequestBody TopicInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	
            TopicInfo detail = topicInfoService.getById(param.getId());
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
    public ResultJson saveTopicInfo(@RequestBody TopicInfoParam param){
        try {
            // [判断条件]根据业务补充...
        	Date curDate = new Date();
        	TopicInfo topicInfo = new TopicInfo();
            BeanUtils.copyProperties(param, topicInfo);
            topicInfo.setCreateTime(curDate);
            topicInfo.setUpdateTime(curDate);
            topicInfoService.save(topicInfo);
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
    public ResultJson updateTopicInfo(@RequestBody TopicInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
        	Date curDate = new Date();
        	TopicInfo topicInfo = new TopicInfo();
            BeanUtils.copyProperties(param, topicInfo);
            topicInfo.setUpdateTime(curDate);
            topicInfoService.updateById(topicInfo);
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
    public ResultJson delTopicInfo(@RequestBody TopicInfoParam param){
        try {
            // [判断条件]根据业务补充...
            if (StringUtils.isNull(param.getId())) {
                log.info("id为空");
                return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
            }
            topicInfoService.removeById(param.getId());
            return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
        } catch (Exception e) {
			log.error("系统异常，请检查", e);
            return new ResultJson(e);
		}
    }
}
