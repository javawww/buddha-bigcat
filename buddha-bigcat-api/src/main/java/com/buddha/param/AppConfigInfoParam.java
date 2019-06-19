package com.buddha.param;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
 /**
 * 
 * APP设置-实体参数
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
 * @时间 2019-06-09
 * @版权 免费开源
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@Getter
@Setter
public class AppConfigInfoParam extends BaseParam {

    /**
     * 代号
     */
	private String codeName;
    /**
     * logo图片
     */
	private String logoPic;
    /**
     * 二维码图片1
     */
	private String qrcodePic1;
    /**
     * 二维码图片2
     */
	private String qrcodePic2;
    /**
     * 引导图片1
     */
	private String bootPic1;
    /**
     * 引导图片2
     */
	private String bootPic2;
    /**
     * 引导图片3
     */
	private String bootPic3;
    /**
     * 封面图片
     */
	private String coverPic;
    /**
     * 功能描述
     */
	private String functionDesc;
    /**
     * 注册协议
     */
	private String registDesc;
    /**
     * 微信公众号
     */
	private String wechatPublicNum;
    /**
     * 客服热线
     */
	private String customerHotline;
    /**
     * 加盟商
     */
	private String allianceBusiness;
    /**
     * 下载地址
     */
	private String downloadLink;
    /**
     * 法人代表
     */
	private String legalMan;
    /**
     * 法人手机号
     */
	private String legalMobile;
    /**
     * 公司名称
     */
	private String companyName;
	/**
     * 访问地址
     */
	private String appLink;
    /**
     * 扫码图片访问
     */
	private String appPic;
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
