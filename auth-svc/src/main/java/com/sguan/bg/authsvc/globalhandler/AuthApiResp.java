package com.sguan.bg.authsvc.globalhandler;

public class AuthApiResp<T> {
    private int code;
    private String msg;

    private String desc;

    private T data;

    public static <T> AuthApiResp<T> success(T data) {
        return new AuthApiResp<>(0, "success", data);
    }
}
