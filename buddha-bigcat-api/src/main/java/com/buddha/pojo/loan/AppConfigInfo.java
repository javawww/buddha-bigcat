package com.buddha.pojo.loan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buddha.component.mybatis.PojoModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
 /**
 * 
 * APP设置-数据库实体对象
 *
 */
@TableName("app_config_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class AppConfigInfo extends PojoModel<AppConfigInfo> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
    /**
     * 代号
     */
	@TableField("code_name")
	private String codeName;
    /**
     * 名称
     */
	private String name;
    /**
     * logo图片
     */
	@TableField("logo_pic")
	private String logoPic;
    /**
     * 二维码图片1
     */
	@TableField("qrcode_pic1")
	private String qrcodePic1;
    /**
     * 二维码图片2
     */
	@TableField("qrcode_pic2")
	private String qrcodePic2;
    /**
     * 引导图片1
     */
	@TableField("boot_pic1")
	private String bootPic1;
    /**
     * 引导图片2
     */
	@TableField("boot_pic2")
	private String bootPic2;
    /**
     * 引导图片3
     */
	@TableField("boot_pic3")
	private String bootPic3;
    /**
     * 封面图片
     */
	@TableField("cover_pic")
	private String coverPic;
    /**
     * 功能描述
     */
	@TableField("function_desc")
	private String functionDesc;
    /**
     * 注册协议
     */
	@TableField("regist_desc")
	private String registDesc;
    /**
     * 微信公众号
     */
	@TableField("wechat_public_num")
	private String wechatPublicNum;
    /**
     * 客服热线
     */
	@TableField("customer_hotline")
	private String customerHotline;
    /**
     * 加盟商
     */
	@TableField("alliance_business")
	private String allianceBusiness;
    /**
     * 下载地址
     */
	@TableField("download_link")
	private String downloadLink;
    /**
     * 法人代表
     */
	@TableField("legal_man")
	private String legalMan;
    /**
     * 法人手机号
     */
	@TableField("legal_mobile")
	private String legalMobile;
    /**
     * 公司名称
     */
	@TableField("company_name")
	private String companyName;
	  /**
     * 访问地址
     */
	@TableField("app_link")
	private String appLink;
    /**
     * 扫码图片访问
     */
	@TableField("app_pic")
	private String appPic;
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
	/**
	 * token key
	 */
	@TableField(exist = false)
	private String token;
}
