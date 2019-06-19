package com.buddha.pojo.member;

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
 * 会员日志记录-数据库实体对象
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
 * @时间 2019-06-13
 * @版权 一群热爱技术的程序猿
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@TableName("member_logs_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberLogsInfo extends PojoModel<MemberLogsInfo> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
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
	@TableField("pixel_ratio")
	private String pixelRatio;
    /**
     * 屏幕宽度
     */
	@TableField("screen_width")
	private Double screenWidth;
    /**
     * 屏幕高度
     */
	@TableField("screen_height")
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
	@TableField("sdk_version")
	private String sdkVersion;
    /**
     * IP地址
     */
	@TableField("ip_address")
	private String ipAddress;
    /**
     * 会员主键
     */
	@TableField("member_id")
	private String memberId;
    /**
     * 会员名称
     */
	@TableField("member_name")
	private String memberName;
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

}
