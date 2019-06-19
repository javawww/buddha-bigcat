package com.buddha.param;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
 /**
 * 
 * 主题话题-数据库实体对象
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
 * @时间 2019-06-06
 * @版权 
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@Getter
@Setter
public class TopicInfoParam extends BaseParam {

    /**
     * 活动主题
     */
	private String topic;
    /**
     * 活动内容介绍
     */
	private String content;
    /**
     * 参与数量
     */
	private Integer amount;
    /**
     * 活动代号
     */
	private String code;
    /**
     * 纬度
     */
	private BigDecimal latitude;
    /**
     * 经度
     */
	private BigDecimal longitude;
    /**
     * 地址
     */
	private String address;
    /**
     * 详细地址
     */
	private String addressDetail;
    /**
     * 封面图片
     */
	private String coverImg;
    /**
     * 活动海报 多张海报用|分割
     */
	private String post;
    /**
     * 活动开始时间
     */
	private Date holdTime;
    /**
     * 活动结束时间
     */
	private Date overTime;
    /**
     * 举办状态：1-未开始 2-进行中 3-已结束
     */
	private Integer holdStatus;
    /**
     * 收费类型： 1-免费 2-女免费男AA 3-女半价男AA
     */
	private Integer chargeType;
    /**
     * 收费方式
     */
	private String chargeDesc;
    /**
     * 是否取消 1-正常 2-取消
     */
	private Integer isCancel;
    /**
     * 取消原因
     */
	private String cancelExplain;
    /**
     * 是否删除 0-正常 1-删除
     */
	private Integer isDel;
    /**
     * 会员id
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
