package com.buddha.pojo.topic;

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
 * @时间 2019-06-04
 * @版权 一群热爱技术的程序猿
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@TableName("topic_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class TopicInfo extends PojoModel<TopicInfo> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
	private String id;
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
	@TableField("address_detail")
	private String addressDetail;
    /**
     * 封面图片
     */
	@TableField("cover_img")
	private String coverImg;
    /**
     * 活动海报 多张海报用|分割
     */
	private String post;
    /**
     * 活动开始时间
     */
	@TableField("hold_time")
	private Date holdTime;
    /**
     * 活动结束时间
     */
	@TableField("over_time")
	private Date overTime;
    /**
     * 举办状态：1-未开始 2-进行中 3-已结束
     */
	@TableField("hold_status")
	private Integer holdStatus;
    /**
     *  状态： 0-审核中 1-通过 2-拒绝
     */
	private Integer status;
    /**
     * 收费类型： 1-免费 2-女免费男AA 3-女半价男AA
     */
	@TableField("charge_type")
	private Integer chargeType;
    /**
     * 收费方式
     */
	@TableField("charge_desc")
	private String chargeDesc;
    /**
     * 是否取消 1-正常 2-取消
     */
	@TableField("is_cancel")
	private Integer isCancel;
    /**
     * 取消原因
     */
	@TableField("cancel_explain")
	private String cancelExplain;
    /**
     * 是否删除 1-正常 2-删除
     */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 会员id
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
