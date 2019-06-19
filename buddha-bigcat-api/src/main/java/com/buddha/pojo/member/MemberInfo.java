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
 * 会员基本信息-数据库实体对象
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
@TableName("member_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberInfo extends PojoModel<MemberInfo> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
    /**
     * 父id
     */
	@TableField("parent_id")
	private String parentId;
    /**
     * 公众平台union id
     */
	@TableField("union_id")
	private String unionId;
    /**
     * 微信openid
     */
	@TableField("open_id")
	private String openId;
    /**
     * 推荐码
     */
	@TableField("recommend_code")
	private String recommendCode;
    /**
     * 微信access token
     */
	@TableField("access_token")
	private String accessToken;
    /**
     * 密码
     */
	private String password;
    /**
     * 邮箱地址
     */
	private String email;
    /**
     * 等级：0-7颗星 默认0颗 其他根据认证审核
     */
	private Integer level;
    /**
     * 是否管理员 1-非管理员 2-平台管理员
     */
	@TableField("is_admin")
	private Integer isAdmin;
    /**
     * 是否认证 0-待认证 1-认证通过 2-认证驳回
     */
	@TableField("is_certification")
	private Integer isCertification;
    /**
     * 是否隐私保护 1-公开 2-隐私保护
     */
	@TableField("is_privacy")
	private Integer isPrivacy;
    /**
     * 自我介绍
     */
	private String introduction;
    /**
     * 昵称
     */
	@TableField("nick_name")
	private String nickName;
    /**
     * 头像图片
     */
	private String avatar;
    /**
     * 微信地区
     */
	private String country;
    /**
     * 真实头像图片
     */
	@TableField("real_avatar")
	private String realAvatar;
    /**
     * 手机号
     */
	private String mobile;
    /**
     * 性别 0-未知 1-男性 -女性
     */
	private Integer gender;
    /**
     * 纬度，范围为 -90~90，负数表示南纬
     */
	private BigDecimal latitude;
    /**
     * 经度，范围为 -180~180，负数表示西经
     */
	private BigDecimal longitude;
    /**
     * 真实姓名
     */
	@TableField("real_name")
	private String realName;
    /**
     * 身份证正面图片
     */
	@TableField("identity_front")
	private String identityFront;
    /**
     * 身份证反面图片
     */
	@TableField("identity_back")
	private String identityBack;
    /**
     * 名片图片
     */
	@TableField("business_card")
	private String businessCard;
    /**
     * 最后登录ip
     */
	@TableField("last_login_ip")
	private String lastLoginIp;
    /**
     * 最后登录时间
     */
	@TableField("last_login_time")
	private Date lastLoginTime;
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
