package com.starforum.entity;

public class StateCode {
    public static final int SUCCESS = 2000; //成功
    public static final int ERROR = 2001;  //失败
    public static final int LOGIN_ERROR = 2002; //用户名或密码错误
    public static final int VALIDATE_FAILED = 2003;  //参数校验失败
    public static final int UNAUTHORIZED = 2004; //暂未登录或token过期
    public static final int FORBIDDEN = 2005;  //没有权限
}
