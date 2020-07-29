package com.houli.common.config;

public class Constant {
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS ="redis";

    public static String LOG_ERROR = "error";
    
    //无权访问
    public static int STATUS_NOT_AUTH = 403;
    //页面未找到
    public static int STATUS_NOT_FOUND = 404;
    //服务器错误
    public static int STATUS_SERVER_ERROR = 500;
    //登录超时
    public static int STATUS_TIMEOUT = 504;
    
}
