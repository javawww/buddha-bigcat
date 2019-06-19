package com.buddha.component.common.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    public static final String KEY_SESSION_DATA = "SESSION_DATA";

    /**
     * 获取当前登录用户用户ID
     */
    public static String getLoginUserId(HttpServletRequest request) {
        //return getLoginUser(request).getId();
        //todo :待实现
        return "";//
    }

    /**
     * 获取当前的企业ID
     *
     * @param request
     * @return
     */
    public static int getEntId(HttpServletRequest request) {
        //return getLoginUser(request).getId();
        //todo :待实现
        return 0;//
    }

    /**
     * 设置当前的组织ID
     *
     * @param request
     * @return
     */
    public static String getOrgId(HttpServletRequest request) {
        //return getLoginUser(request).getId();
        //todo :待实现
        return "";//
    }

    /**
     * 设置当前的项目ID
     *
     * @param request
     * @return
     */
    public static int getPrjId(HttpServletRequest request) {
        //return getLoginUser(request).getId();
        //todo :待实现
        return 1;//
    }

    /**
     * 判断当前用户是否已经登录
     *
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
        //return getLoginUser(request).getOrgId();
        //todo :待实现
        return true;//
    }

}