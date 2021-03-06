package com.buddha.pojo.market;

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
 * 跑马灯-数据库实体对象
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
@TableName("market_marquee_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class MarketMarqueeInfo extends PojoModel<MarketMarqueeInfo> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
    /**
     * 封面图片
     */
	@TableField("cover_pic")
	private String coverPic;
    /**
     * 内容
     */
	private String content;
    /**
     * 已读数量
     */
	@TableField("already_read")
	private Integer alreadyRead;
    /**
     * 点赞数量
     */
	@TableField("thumbs_up")
	private Integer thumbsUp;
    /**
     * 评论数量
     */
	private Integer comment;
    /**
     * 排序
     */
	private Integer sorts;
    /**
     * APP主键
     */
	@TableField("app_id")
	private String appId;
    /**
     * 名称
     */
	private String name;
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
