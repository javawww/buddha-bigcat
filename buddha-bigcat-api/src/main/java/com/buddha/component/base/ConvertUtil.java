package com.buddha.component.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class ConvertUtil {

   /**
     * Json串转MAP
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> json2Map(String strJson) {
        if (isNull(strJson)) {
            return new HashMap<String, String>();
        }
        return JSON.parseObject(strJson, Map.class);
    }

    /**
     * JSON转List<Map<String, String>>
     */
    @SuppressWarnings({ "rawtypes" })
    public static List<Map<String, Object>> json2List(String strJson) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (isNull(strJson)) {
            return result;
        }
        List jsonList = JSON.parseObject(strJson, List.class);
        for (Object json : jsonList) {
            result.add(jsonstr2Map(nvl(json)));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonstr2Map(String strJson) {
        if (isNull(strJson)) {
            return new HashMap<String, Object>();
        }
        return JSON.parseObject(strJson, Map.class);
    }
    /**
     * 字符串变换
     */
    public static String nvl(Object obj) {
        return nvl(obj, "");
    }
    /**
     * 字符串变换
     */
    public static String nvl(Object obj, String strConv) {
        if (obj == null || "".equals(obj)) {
            return strConv;
        } else {
            return obj.toString();
        }
    }
    /**
     * 空串判断（true：空、false：非空）
     */
    public static boolean isNull(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 空串判断（true：非空、false：空）
     */
    public static boolean isNotNull(String value) {
        return ! isNull(value);
    }

    public static java.sql.Date utilDateToSqlDate(java.util.Date UtilDate)
    {
        return new java.sql.Date(UtilDate.getTime());
    }
    public static java.sql.Time utilTimeToSqlTime(java.util.Date UtilDate)
    {
        return new java.sql.Time(UtilDate.getTime());
    }
    public static java.sql.Timestamp utilTimeToSqlTimespan(java.util.Date UtilDate)
    {
        return new java.sql.Timestamp(UtilDate.getTime());
    }
}