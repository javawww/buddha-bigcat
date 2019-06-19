package com.buddha.param;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
 /**
 * 
 * 群聊消息-实体参数
 *
 * #############################################################################
 *
 * CopyRight (C) 2019 ShenZhen LoveJava Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 小程序开发，企业系统开发，安卓苹果APP开发，服务器部署<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2019-06-17
 * @版权 免费开源
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@Getter
@Setter
public class MessageGroupInfoParam extends BaseParam {

    /**
     * 房间号：某活动 某组织等
     */
	private String roomId;
    /**
     * 消息来源
     */
	private String fromId;
    /**
     * 消息去向
     */
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
     * 删除
     */
	private Integer isDel;
    /**
     * 创建人id
     */
	private String createId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;

}
