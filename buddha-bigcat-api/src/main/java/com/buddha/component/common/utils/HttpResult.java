package com.buddha.component.common.utils;

import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSON;
import com.buddha.component.common.enums.ResultStatusEnum;

public class HttpResult {

	/**
	 * 返回代码
	 */
	private Integer code;

	/**
	 * 返回内容
	 */
	private String data;

	public HttpResult() {
	}

	public HttpResult(Integer code, String data) {
		this.code = code;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "返回数据内容： {code:" + code + ",data:" + data + "}";
	}

	/**
	 * 转换为ResultJson对象
	 * 
	 * @return
	 */
	public ResultJson toResultJson() {
		if (HttpStatus.OK.value() != this.getCode()) {
			return new ResultJson(ResultStatusEnum.SERVER_ERROR);
		}
		return JSON.parseObject(this.getData(), ResultJson.class);
	}
}
