package com.buddha.component.common.enums;

import com.alibaba.fastjson.JSON;


/**
 * 
 * 通用业务返回代码，提示信息(系统中通用)
 * 
 * #############################################################################
 * CopyRight (C) 2018 ShenZhen ZhiZaoJianZhu Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市智造建筑信息科技有限公司设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 李金亮(HOVER)
 * @时间 2018-7-11
 * @版权 深圳市智造建筑信息科技有限公司(www.icbi.xin)
 */
public enum ResultStatusEnum {
	/**
	 * 通用：操作成功
	 */
	COMMON_SUCCESS(600, "成功"),
	/**
	 * 通用：操作失败
	 */
	COMMON_FAIL(601, "失败"),
	/**
	 * 参数错误
	 */
	PARAMETER_ERROR(602, "参数错误"),
	/**
	 * 服务器错误
	 */
	SERVER_ERROR(603, "服务器错误，请稍后尝试"),
//	/**
//	 * 手机号码已存在
//	 */
//	TELEPHONE_EXIST(604, "手机号码已存在"),
//	/**
//	 * 手机号码不存在
//	 */
//	TELEPHONE_NOT_EXIST(605, "手机号码不存在"),
//	/**
//	 * 手机号码错误
//	 */
//	TELEPHONE_ERROR(606, "手机号码错误"),
//	/**
//	 * 账号或密码错误
//	 */
//	ACCOUNT_AND_PWD_ERROR(607, "账号或密码错误"),
//	/**
//	 * 短信验证码错误
//	 */
//	SMS_VERIFICATION_CODE_ERROR(608, "短信验证码错误"),
//	/**
//	 * 短信验证码过期
//	 */
//	SMS_VERIFICATION_CODE_EXPIRE(609, "短信验证码已过期"),
//	/**
//	 * token为空
//	 */
	TOKEN_IS_NULL(610, "token为空"),
//	/**
//	 * token过期
//	 */
	TOKEN_IS_EXPIRE(611, "token过期"),
//	/**
//	 * 用户已被锁定
//	 */
//	USER_IS_LOCKING(612, "用户已被锁定"),
//	/**
//	 * 身份证号已存在
//	 */
//	IDCORD_IS_EXIST(613, "身份证号已存在"),
//	/**
//	 * 身份证号错误
//	 */
//	IDCORD_ERROR(614, "身份证号错误"),
//	/**
//	 * 没有权限
//	 */
//	NOT_AUTHOR(615, "没有系统权限"),
//	/**
//	 * 数据已经存在
//	 */
	DATA_IS_EXIST(616, "数据已经存在"),
//	/**
//	 * 数据不存在
//	 */
	DATA_NOT_EXIST(617, "数据不存在");
//	/**
//	 * 未加入项目
//	 */
//	NOT_ADD_PROJECT(618, "未加入项目"),
//	/**
//	 * 项目已过期
//	 */
//	PROJECT_EXPIRE(619, "项目已过期"),
//	/**
//	 * 项目ID为空
//	 */
//	PROJECT_ID_IS_NULL(620, "项目ID为空"),
//	/**
//	 * 未实名认证
//	 */
//	USER_NOT_AUTHENTICATION(621, "用户未实名认证"),
//	/**
//	 * 已实名认证
//	 */
//	USER_AUTHENTICATION(622, "用户已实名认证"),
//	
//	/**
//	 * 此部位存在工单
//	 */
//	ALREADY_BIND_WORKORDER(623, "此部位已存在工单"),
//	/**
//	 * 此部位存在工单
//	 */
//	INVITE_CODE_FORBIDDEN(624, "邀请码错误"),
//	;

	/**
	 * 操作代码
	 */
	private final int code;

	/**
	 * 提示信息
	 */
	private final String msg;

	private ResultStatusEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 通过业务代码获取业务操作枚举
	 * 
	 * @param code
	 *            业务代码
	 * @return 业务枚举
	 */
	public static ResultStatusEnum getResultStatusEnum(int code) {
		for (ResultStatusEnum item : ResultStatusEnum.values()) {
			if (item.code == code) {
				return item;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
