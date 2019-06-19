package com.buddha.component.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.exception.BaseException;

/**
 * @author peter
 *
 * Json返回结果基类
 *
 */
public class ResultJson {

	/**
	 * 操作代码(具体参考返回代码常量接口定义)
	 */
	private Integer code;

	/**
	 * 数据对象
	 */
	private Object data;

	/**
	 * 提示消息
	 */
	private String msg;

	public ResultJson() {
	}

	/**
	 * 构造函数
	 * 
	 * @param code
	 *            操作代码
	 */
	public ResultJson(Integer code) {
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param code
	 *            操作代码
	 * @param data
	 *            数据对象
	 */
	public ResultJson(Integer code, Object data) {
		this(code);
		this.data = data;
	}

	/**
	 * 构造函数
	 * 
	 * @param code
	 *            操作代码
	 * @param msg
	 *            提示消息
	 */
	public ResultJson(Integer code, String msg) {
		this(code);
		this.msg = msg;
	}

	/**
	 * 构造函数
	 * 
	 * @param code
	 *            操作代码
	 * @param data
	 *            数据对象
	 * @param msg
	 *            提示消息
	 */
	public ResultJson(Integer code, Object data, String msg) {
		this(code, data);
		this.msg = msg;
	}

	/**
	 * 构造函数
	 * 
	 * @param rs
	 *            业务枚举
	 */
	public ResultJson(ResultStatusEnum rs) {
		this(rs.getCode(), rs.getMsg());
	}

	/**
	 * 构造函数
	 * 
	 * @param rs
	 *            业务枚举
	 * @param data
	 *            数据对象
	 */
	public ResultJson(ResultStatusEnum rs, Object data) {
		this(rs.getCode(), data, rs.getMsg());
	}

	/**
	 * 构造函数
	 * 
	 * @param rs
	 *            业务枚举
	 * @param msg
	 *            提示消息
	 */
	public ResultJson(ResultStatusEnum rs, String msg) {
		this(rs.getCode(), msg);
	}

	/**
	 * 构造方法<br />
	 * 异常返回封装（当service需要返回提示信息时返回BaseException类型异常，其他的为系统未知异常，直接提示系统异常）
	 * 
	 * @param ex
	 *            异常对象
	 */
	public ResultJson(Exception ex) {
		if (ex instanceof BaseException) {// 是自定义返回消息异常
			BaseException exception = ((BaseException) ex);
			this.code = exception.getCode();
			this.msg = exception.getMsg();
		} else {
			this.code = ResultStatusEnum.COMMON_FAIL.getCode();
			this.msg = ResultStatusEnum.COMMON_FAIL.getMsg();
		}
	}

	/**
	 * 请求是否成功<br />
	 * 操作代码为600时，表示操作成功，并返回true
	 * 
	 * @return 返回请求是否成功（true|false）
	 */
	@JSONField(serialize = false)
	public boolean isSuccess() {
		if (this.getCode() != null && this.getCode().intValue() == ResultStatusEnum.COMMON_SUCCESS.getCode()) {
			return true;
		}
		return false;
	}

	/**
	 * 请求是否失败<br />
	 * 操作代码不为600时，表示操作失败，并返回true
	 * 
	 * @return 返回请求是否失败（true|false）
	 */
	@JSONField(serialize = false)
	public boolean isFail() {
		return !this.isSuccess();
	}

	/**
	 * 把Object的data参数json字符串转换为java对象<br />
	 * 如果data参数为空，返回空（null）
	 * 
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	@JSONField(serialize = false)
	public <T> T toObject(Class<T> clazz) {
		if (this.getData() != null) {
			JSONObject jsonObject = (JSONObject) this.getData();
			return jsonObject.toJavaObject(clazz);
		}
		return null;
	}

	/**
	 * 把Object的data参数json字符串转换为java集合<br />
	 * 如果data参数为空，返回空（null）
	 * 
	 * @param clazz
	 *            转换类型
	 * @return
	 */
	@JSONField(serialize = false)
	public <T> List<T> toObjectList(Class<T> clazz) {
		if (this.getData() != null) {
			JSONArray objectList = (JSONArray) this.getData();
			return objectList.toJavaList(clazz);
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
