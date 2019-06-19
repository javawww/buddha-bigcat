package com.buddha.component.common.enums;

import com.buddha.component.common.utils.StringUtils;

import lombok.Getter;

/**
 * 不拦截请求
 */
@Getter
public enum NoTokenVerifyURIEnum {
	/**
	 * 获取临时授权token（阿里云oss系统授权token）
	 */
	FILE_AUTH_TOKEN("/fileservice/get-temp-auth-token"),
	/**
	 * APP端H5端请求接口
	 */
//	APP_API("/api/app/"),
	
	APP_ERROR("/error"),
	;

	/**
	 * 状态值
	 */
	private final String value;

	NoTokenVerifyURIEnum(String value) {
		this.value = value;
	}

	/**
	 * 通过状态值获取状态枚举
	 * 
	 * @param value value
	 * @return NoTokenVerifyURIEnum
	 */
	public static NoTokenVerifyURIEnum getStatusEnum(String value) {
		if (StringUtils.isNull(value)) {
			return null;
		}
		for (NoTokenVerifyURIEnum enums : NoTokenVerifyURIEnum.values()) {
			if (value.equals(enums.getValue())) {
				return enums;
			}
		}
		return null;
	}

}
