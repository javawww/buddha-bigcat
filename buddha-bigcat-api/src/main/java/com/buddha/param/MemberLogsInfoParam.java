package com.buddha.param;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
 /**
 * 
 * -实体参数
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
 * @时间 2019-06-13
 * @版权 免费开源
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@Getter
@Setter
public class MemberLogsInfoParam extends BaseParam {

    /**
     * 手机品牌
     */
	private String brand;
    /**
     * 手机型号
     */
	private String model;
    /**
     * 设备像素比
     */
	private String pixelRatio;
    /**
     * 屏幕宽度
     */
	private Double screenWidth;
    /**
     * 屏幕高度
     */
	private Double screenHeight;
    /**
     * 应用设置的语言
     */
	private String language;
    /**
     * 引擎版本号
     */
	private String version;
    /**
     * 操作系统版本
     */
	private String system;
    /**
     * 客户端平台ios、android
     */
	private String platform;
    /**
     * 客户端基础库版本
     */
	private String sdkVersion;
    /**
     * IP地址
     */
	private String ipAddress;
    /**
     * 会员主键
     */
	private String memberId;
    /**
     * 会员名称
     */
	private String memberName;
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
