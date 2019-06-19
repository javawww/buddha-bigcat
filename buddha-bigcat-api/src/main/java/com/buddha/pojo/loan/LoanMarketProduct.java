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
 * 贷款超市-数据库实体对象
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
 * @时间 2019-06-08
 * @版权 一群热爱技术的程序猿
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@TableName("loan_market_product")
@Data
@EqualsAndHashCode(callSuper = true)
public class LoanMarketProduct extends PojoModel<LoanMarketProduct> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
    /**
     * 标题
     */
	private String title;
    /**
     * logo图片
     */
	@TableField("logo_pic")
	private String logoPic;
    /**
     * 详情内容
     */
	private String content;
    /**
     * 背景图片
     */
	@TableField("bg_pic")
	private String bgPic;
    /**
     * 标签数组
     */
	@TableField("label_arr")
	private String labelArr;
    /**
     * 可借额度
     */
	@TableField("borrow_salary_range")
	private String borrowSalaryRange;
    /**
     * 最高可借
     */
	@TableField("high_limit")
	private BigDecimal highLimit;
    /**
     * 最低可借
     */
	@TableField("low_limit")
	private BigDecimal lowLimit;
    /**
     * 月利率
     */
	@TableField("month_rate")
	private BigDecimal monthRate;
    /**
     * 申请数量
     */
	@TableField("apply_amount")
	private Integer applyAmount;
    /**
     * 浏览数量
     */
	@TableField("browse_amount")
	private Integer browseAmount;
    /**
     * 点赞数量
     */
	@TableField("praise_amount")
	private Integer praiseAmount;
    /**
     * 额度比重
     */
	@TableField("salary_scale")
	private Double salaryScale;
    /**
     * 放款速度
     */
	@TableField("speed_scale")
	private Double speedScale;
    /**
     * 通过率
     */
	@TableField("pass_scale")
	private Double passScale;
    /**
     * 负责人
     */
	@TableField("charge_person")
	private String chargePerson;
    /**
     * 联系方式
     */
	@TableField("charge_mobile")
	private String chargeMobile;
    /**
     * 供货公司
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 发货地址
     */
	@TableField("company_address")
	private String companyAddress;
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
