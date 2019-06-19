package com.buddha.component.common.enums;

/**
 * 
 * 数据状态枚举
 */
public enum StatusEnum {
	/**
	 * 审核中
	 */
	AUDITING(0, "审核中"),
	/**
	 * 通过
	 */
	PASS(1, "通过"),
	
	/**
	 * 拒绝
	 */
	REFUSE(2, "拒绝");

	/**
	 * 状态值
	 */
	private final Integer value;

	/**
	 * 说明
	 */
	private final String desc;

	private StatusEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 通过状态值获取状态枚举
	 * 
	 * @param value
	 * @return
	 */
	public static StatusEnum getStatusEnum(Integer value) {
		if (value == null) {
			return null;
		}
		for (StatusEnum enums : StatusEnum.values()) {
			if (value == enums.getValue()) {
				return enums;
			}
		}
		return null;
	}

	public Integer getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
