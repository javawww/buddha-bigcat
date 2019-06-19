package com.buddha.component.common.exception;

import com.buddha.component.common.enums.ResultStatusEnum;


/**
 * 
 * 异常基础类
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
@SuppressWarnings("serial")
public class BaseException extends RuntimeException {

	/**
	 * 返回错误码
	 */
	private Integer code;

	/**
	 * 返回信息
	 */
	private String msg;
	
	/**
	 * 构造 （默认返回码：601）
	 * 
	 * @param msg
	 *            提示信息
	 */
	public BaseException(String msg) {
		code = ResultStatusEnum.COMMON_FAIL.getCode();
		this.msg = msg;
	}

	/**
	 * 构造
	 * 
	 * @param enumCode
	 * @param msg
	 */
	public BaseException(ResultStatusEnum enumCode) {
		super(enumCode.getMsg());
		this.code = enumCode.getCode();
		this.msg = enumCode.getMsg();
	}

	/**
	 * 构造
	 * 
	 * @param enumCode
	 * @param msg
	 */
	public BaseException(ResultStatusEnum enumCode, String msg) {
		super(msg);
		this.msg = msg;
		this.code = enumCode.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
