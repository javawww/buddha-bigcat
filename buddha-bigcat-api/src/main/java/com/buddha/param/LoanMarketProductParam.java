package com.buddha.param;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
 /**
 * 
 * 贷款超市-实体参数
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
 * @时间 2019-06-08
 * @版权 免费开源
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@Getter
@Setter
public class LoanMarketProductParam extends BaseParam {

    /**
     * 标题
     */
	private String title;
    /**
     * logo图片
     */
	private String logoPic;
    /**
     * 详情内容
     */
	private String content;
    /**
     * 背景图片
     */
	private String bgPic;
    /**
     * 标签数组
     */
	private String labelArr;
    /**
     * 可借额度
     */
	private String borrowSalaryRange;
    /**
     * 最高可借
     */
	private BigDecimal highLimit;
    /**
     * 最低可借
     */
	private BigDecimal lowLimit;
    /**
     * 月利率
     */
	private BigDecimal monthRate;
    /**
     * 申请数量
     */
	private Integer applyAmount;
    /**
     * 浏览数量
     */
	private Integer browseAmount;
    /**
     * 点赞数量
     */
	private Integer praiseAmount;
    /**
     * 额度比重
     */
	private Double salaryScale;
    /**
     * 放款速度
     */
	private Double speedScale;
    /**
     * 通过率
     */
	private Double passScale;
    /**
     * 负责人
     */
	private String chargePerson;
    /**
     * 联系方式
     */
	private String chargeMobile;
    /**
     * 供货公司
     */
	private String companyName;
    /**
     * 发货地址
     */
	private String companyAddress;
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
