package com.buddha.param;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
 /**
 * 
 * 聊天室-实体参数
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
 * @时间 2019-06-16
 * @版权 免费开源
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@Getter
@Setter
public class MessageRoomInfoParam extends BaseParam {

    /**
     * 房间密码
     */
	private String passCode;
    /**
     * 代号标记
     */
	private String mark;
    /**
     * 收费金额
     */
	private BigDecimal charge;
    /**
     * 图片
     */
	private String coverPic;
    /**
     * 内容
     */
	private String content;
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
