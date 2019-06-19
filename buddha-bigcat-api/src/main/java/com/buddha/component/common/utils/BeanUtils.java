package com.buddha.component.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * 对象处理工具类
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
public class BeanUtils {

	private static final String OBJECT_CLASS_NAME = "java.lang.object";

	/**
	 * 获取类的变量集合
	 * 
	 * @param clazz
	 *            类型
	 * @return
	 */
	public static List<Field> getFieldList(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		Class<?> tempClass = clazz;
		while (tempClass != null && !tempClass.getName().toLowerCase().equals(OBJECT_CLASS_NAME)) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}
		return fieldList;
	}

	/**
	 * 获取类的变量名称集合
	 * 
	 * @param clazz
	 *            类型
	 * @return
	 */
	public static List<String> getFieldNameList(Class<?> clazz) {
		List<Field> fieldList = getFieldList(clazz);
		List<String> fieldNameList = new ArrayList<String>();
		for (Field field : fieldList) {
			fieldNameList.add(field.getName());
		}
		return fieldNameList;
	}

	/**
	 * 获取类的方法集合
	 * 
	 * @param clazz
	 *            类型
	 * @return
	 */
	public static List<Method> getMethodList(Class<?> clazz) {
		List<Method> methodList = new ArrayList<Method>();
		Class<?> tempClass = clazz;
		while (tempClass != null && !tempClass.getName().toLowerCase().equals(OBJECT_CLASS_NAME)) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			methodList.addAll(Arrays.asList(tempClass.getDeclaredMethods()));
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}
		return methodList;
	}

	/**
	 * 获取类的方法名称集合
	 * 
	 * @param clazz
	 *            类型
	 * @return
	 */
	public static List<String> getMethodNameList(Class<?> clazz) {
		List<Method> methodList = getMethodList(clazz);
		List<String> methodNameList = new ArrayList<String>();
		for (Method method : methodList) {
			methodNameList.add(method.getName());
		}
		return methodNameList;
	}

	/**
	 * 获取对象的变量值
	 * 
	 * @param clazzInstance
	 *            对象实例
	 * @param fieldName
	 *            属性名称
	 * @param fieldType
	 *            属性类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObjectFieldValue(Object clazzInstance, String fieldName, Class<T> fieldType) {
		Class<?> tempClass = clazzInstance.getClass();
		Field field = null;
		while (tempClass != null && !tempClass.getName().toLowerCase().equals(OBJECT_CLASS_NAME)) {
			try {
				field = tempClass.getDeclaredField(fieldName);
				if (field != null) {
					field.setAccessible(true);
					return (T) field.get(clazzInstance);
				}
			} catch (Exception ex) {
			}
			tempClass = tempClass.getSuperclass();
		}
		return null;
	}

	/**
	 * 调用对象的方法（无参数）
	 * 
	 * @param clazzInstance
	 *            对象实例
	 * @param methodName
	 *            方法名称
	 * @param methodReturnType
	 *            返回值类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObjectMethodValue(Object clazzInstance, String methodName, Class<T> methodReturnType) {
		Class<?> tempClass = clazzInstance.getClass();
		Method method = null;
		while (tempClass != null && !tempClass.getName().toLowerCase().equals(OBJECT_CLASS_NAME)) {
			try {
				method = tempClass.getDeclaredMethod(methodName);
				if (method != null) {
					method.setAccessible(true);
					return (T) method.invoke(clazzInstance);
				}
			} catch (Exception ex) {
			}
			tempClass = tempClass.getSuperclass();
		}
		return null;
	}

	/**
	 * 调用对象的方法（有参数）
	 * 
	 * @param clazzInstance
	 *            对象实例
	 * @param methodName
	 *            方法名称
	 * @param methodReturnType
	 *            返回值类型
	 * @param paramClasses
	 *            参数类型数组
	 * @param params
	 *            参数值数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObjectMethodValue(Object clazzInstance, String methodName, Class<T> methodReturnType, Class<?>[] paramClasses, Object[] params) {
		Class<?> tempClass = clazzInstance.getClass();
		Method method = null;
		while (tempClass != null && !tempClass.getName().toLowerCase().equals(OBJECT_CLASS_NAME)) {
			try {
				method = tempClass.getDeclaredMethod(methodName, paramClasses);
				if (method != null) {
					method.setAccessible(true);
					return (T) method.invoke(clazzInstance, params);
				}
			} catch (Exception ex) {
			}
			tempClass = tempClass.getSuperclass();
		}
		return null;
	}
}
