package com.buddha.pojo.message;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buddha.component.mybatis.PojoModel;
import com.buddha.pojo.base.SystemAdmin;

import lombok.Data;
import lombok.EqualsAndHashCode;
 /**
 * 
 * 群聊消息-数据库实体对象
 *
 * #############################################################################
 *
 * CopyRight (C) 2018 ShenZhen LoveJava Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 小程序开发，企业系统开发，安卓苹果APP开发，服务器部署<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2019-06-17
 * @版权 一群热爱技术的程序猿
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@TableName("message_group_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageGroupInfo extends PojoModel<MessageGroupInfo> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
    /**
     * 房间号：某活动 某组织等
     */
	@TableField("room_id")
	private String roomId;
    /**
     * 消息来源
     */
	@TableField("from_id")
	private String fromId;
    /**
     * 消息去向
     */
	@TableField("to_id")
	private String toId;
    /**
     * 消息内容
     */
	private String message;
    /**
     * 图片地址
     */
	private String url;
    /**
     * 状态 0-已发送 1-已阅读 2-未阅读
     */
	@TableField("see_status")
	private Integer seeStatus;
    /**
     * 类型：1-文本 2-图片
     */
	private Integer type;
    /**
     * 排序
     */
	private Integer sorts;
    /**
     * 名称
     */
	private String name;
    /**
     * APP主键
     */
	@TableField("app_id")
	private String appId;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 删除
     */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 创建人id
     */
	@TableField("create_id")
	private String createId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	// 非数据库属性
	@TableField(exist = false)
	private SystemAdmin user;
	
	@TableField(exist = false)
	private String msgType;
	
	@TableField(exist = false)
	private Date datetime;
	
	@TableField(exist = false)
	private String msg;
}
