package com.buddha.param;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseParam {

	private String token;// 请求token

	private Integer page = 1;// 页数（从1开始）

	private Integer pageSize = 25;// 分页大小（每页显示条数）

	private String id;// 数据ID
	
	private String openId;// 用户小程序唯一标识

	private String projectId;// 项目ID
	
	private String appId;// APP主键
	
	private String appCode;//APP代号

	private String personAccountId;// 个人用户ID

	private String projectAccountId;// 平台用户ID

	private String accountName;// 平台用户名称

	private String ids;// 多个id参数，中间用|隔开

	private String sort = "id";// 排序列

	private Integer sorts = 0;// 数据库的sort列（因为是int类型和排序列冲突，故加了s）

	private String order = "ASC";// 排序方式（asc，desc）

	private Boolean flag = false;// 布尔参数（每个模块可以自由使用，例如：是否查询今天的数据、是否查询成功的数据、是否查询明细）

	private Integer status;// 状态

	private String remark;// 备注

	private Integer productType;// 产品类型；1-总包；2-分包；
	
	private String accountId; // 公司用户id
	
	private String chamberId; // 商会id
	
	private String name; // 名称

	public BaseParam() {
	}

	public BaseParam(String id) {
		this.id = id;
	}

	public BaseParam(String sort, String order) {
		this.sort = sort;
		this.order = order;
	}

	/**
	 * 是否是asc排序
	 * 
	 * @return
	 */
	public boolean isAsc() {
		return "asc".equalsIgnoreCase(this.order);
	}

	/**
	 * 项目ID是否为空
	 * 
	 * @return
	 */
	@JSONField(serialize = false)
	public boolean projectIdIsNull() {
		return StringUtils.isEmpty(this.projectId);
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
