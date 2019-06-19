package com.buddha.param;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
 /**
 * 
 * 会员基本信息-实体参数
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
public class MemberInfoParam extends BaseParam {

    /**
     * 父id
     */
	private String parentId;
    /**
     * 公众平台union id
     */
	private String unionId;
    /**
     * 微信openid
     */
	private String openId;
    /**
     * 推荐码
     */
	private String recommendCode;
    /**
     * 微信access token
     */
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
	private Integer isAdmin;
    /**
     * 是否认证 0-待认证 1-认证通过 2-认证驳回
     */
	private Integer isCertification;
    /**
     * 是否隐私保护 1-公开 2-隐私保护
     */
	private Integer isPrivacy;
    /**
     * 自我介绍
     */
	private String introduction;
    /**
     * 昵称
     */
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
	private String realName;
    /**
     * 身份证正面图片
     */
	private String identityFront;
    /**
     * 身份证反面图片
     */
	private String identityBack;
    /**
     * 名片图片
     */
	private String businessCard;
    /**
     * 最后登录ip
     */
	private String lastLoginIp;
    /**
     * 最后登录时间
     */
	private Date lastLoginTime;
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
