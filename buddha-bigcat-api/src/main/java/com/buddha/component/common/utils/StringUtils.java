package com.buddha.component.common.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.lang.Nullable;


/**
 * 
 * 字符串处理工具类
 */
public final class StringUtils {

	
	/**
	 * Check whether the given {@code String} is empty.
	 * <p>This method accepts any Object as an argument, comparing it to
	 * {@code null} and the empty String. As a consequence, this method
	 * will never return {@code true} for a non-null non-String object.
	 * <p>The Object signature is useful for general attribute handling code
	 * that commonly deals with Strings but generally has to iterate over
	 * Objects since attributes may e.g. be primitive value objects as well.
	 * @param str the candidate String
	 * @since 3.2.1
	 */
	public static boolean isNull(@Nullable Object str) {
		return (str == null || "".equals(str));
	}
	
	public static boolean isNotNull(@Nullable Object str) {
		return !isNull(str);
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return 不为空返回true,反之
	 */
	public static boolean isNotNull(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		if (str.length() == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断数字是否为空
	 * @param str
	 * @return 不为空返回true,反之
	 */
	public static boolean isNotNull(Integer val) {
		if (val == null) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @Description: 判断集合为空值或者集合为空
	 * @param @return 参数
	 * @return boolean 返回类型 为空返回false;有值则返回true;
	 * 
	 */
	public static boolean isNotNull(List<?> list) {
		if (null == list || list.isEmpty()) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @Description: 判断集合为空值或者集合为空
	 * @param @return 参数
	 * @return boolean 返回类型 为空返回true;有值则返回false;
	 * 
	 */
	public static boolean isNull(List<?> list) {
		if (null == list || list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return 为空返回true
	 */
	public static boolean isNull(String str) {
		return !isNotNull(str);
	}
	
	/**
	 * 判断数字是否为空
	 * 
	 * @param str
	 * @return 为空返回true
	 */
	public static boolean isNull(Integer str) {
		return !isNotNull(str);
	}



	/**
	 * 过滤文本中的html代码
	 * 
	 * @param str
	 *            带html的字符串
	 * @return
	 */
	public static String filterHtml(String str) {
		String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
		Pattern pattern = Pattern.compile(regxpForHtml);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString().replaceAll("&nbsp;", "").replaceAll(" ", "");
	} 
	/**
     * 检查字符串是否为空
     * <p>为null或者长度为0视为空字符串
     * @param value 要检查的字符串
     * @return
     */
    public static boolean isEmpty(String value) {
        return isEmpty(value, false);
    }
    
    /**
     * 检查字符串是否为空
     * <p>为null或者长度为0视为空字符串
     * @param value 要检查的字符串
     * @param trim 是否去掉头尾的特定字符
     * @param trimChars 要去掉的特定字符
     * @return
     */
    public static boolean isEmpty(String value, boolean trim, char... trimChars) {
        if (trim)
            return value == null || trim(value, trimChars).length() <= 0;
        return value == null || value.length() <= 0;
    }
    
    /**
     * 去除字符串头尾的特定字符
     * 
     * @param value 待处理的字符串
     * @param chars 需要去掉的特定字符
     * @return
     */
    public static String trim(String value, char... chars) {
        return trim(3, value, chars);
    }
    
    /**
     * 去掉头尾空格字符
     * @param value 待处理的字符串
     * @return
     */
    public static String trim(String value) {
        return trim(3, value, ' ');
    }
    
    /**
     * 去掉字符串头尾特定字符
     * @param mode 
     * <li>1: 去掉头部特定字符；
     * <li>2: 去掉尾部特定字符；
     * <li>3: 去掉头尾特定字符；
     * @param value 待处理的字符串
     * @param chars 需要去掉的特定字符
     * @return
     */
    private static String trim(int mode, String value, char... chars) {
        if (value == null || value.length() <= 0)
            return value;

        int startIndex = 0, endIndex = value.length(), index = 0;
        if (mode == 1 || mode == 3) {
            // trim头部
            while (index < endIndex) {
                if (contains(chars, value.charAt(index++))) {
                    startIndex++;
                    continue;
                }
                break;
            }
        }

        if (startIndex >= endIndex)
            return "";

        if (mode == 2 || mode == 3) {
            // trim尾部
            index = endIndex - 1;
            while (index >= 0) {
                if (contains(chars, value.charAt(index--))) {
                    endIndex--;
                    continue;
                }
                break;
            }
        }

        if (startIndex >= endIndex)
            return "";
        if (startIndex == 0 && endIndex == value.length() - 1)
            return value;

        return value.substring(startIndex, endIndex);
    }
    
    private static boolean contains(char[] chars, char chr) {
        if (chars == null || chars.length <= 0)
            return false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == chr)
                return true;
        }
        return false;
    }
    /**
     * 列表转换字符串
     * @param strs
     * @return
     */
    public static String list2String(List<String> strs){
    	StringBuilder sb  = new StringBuilder();
    	if(strs!=null && strs.size() > 0){
    		for (String str : strs) {
				sb.append(str).append("|");
			}
    	}
    	return sb.toString();
    }
    /**
     * 字符串转换列表
     * @param str
     * @return
     */
    public static String[] string2List(String str){
    	if(StringUtils.isNotNull(str)) {
    		String[] strs = str.split("\\|");
    		return strs;
    	}
    	return new String[]{""};
    }
    
    /**
     * 逗号 -> 竖线
     * @param str
     * @return
     */
    public static String comma2vertical(String str) {
    	return str.trim().replace(",","|").replace("，","|").replace(" ","|");
    }
    
    /**
     * 竖线 -> 逗号
     * @return
     */
    public static String vertical2comma(String str) {
    	return str.trim().replace("|",",");
    }
    
    public static void main(String[] args) {
		String[] strarr = string2List(null);
		System.out.println(strarr);
	}
}
