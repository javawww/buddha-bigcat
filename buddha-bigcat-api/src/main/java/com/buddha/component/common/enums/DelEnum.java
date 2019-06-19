package com.buddha.component.common.enums;

/**
 * 
 * 数据状态枚举
 */
public enum DelEnum {
	/**
	 * 正常
	 */
	NORMAL(0, "显示"),
	/**
	 * 禁用
	 */
	DISABLE(1, "删除");

	/**
	 * 状态值
	 */
	private final Integer value;

	/**
	 * 说明
	 */
	private final String desc;

	private DelEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 通过状态值获取状态枚举
	 * 
	 * @param value
	 * @return
	 */
	public static DelEnum getStatusEnum(Integer value) {
		if (value == null) {
			return null;
		}
		for (DelEnum enums : DelEnum.values()) {
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
