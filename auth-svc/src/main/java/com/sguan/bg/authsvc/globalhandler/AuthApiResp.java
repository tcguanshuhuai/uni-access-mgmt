package com.sguan.bg.authsvc.globalhandler;

import lombok.Data;

@Data
public class AuthApiResp<T> {
    private int code;
    private String msg;
    private String desc;
    private T data;



    public static AuthApiResp ok() {
        return code(AuthRespCode.SUCCESS, "", null);
    }

    public static <R> AuthApiResp<R> ok(R data) {
        return code(AuthRespCode.SUCCESS, "", data);
    }

    public static <R> AuthApiResp<R> ok(String desc, R data) {
        return code(AuthRespCode.SUCCESS, "", data);
    }

    public static AuthApiResp code(AuthRespCode respCode) {
        return code(respCode, "", null);
    }

    public static AuthApiResp code(AuthRespCode respCode, String desc) {
        return code(respCode, desc, null);
    }

    public static <R> AuthApiResp<R> code(AuthRespCode respCode, R data) {
        return code(respCode, "", data);
    }

    public static <R> AuthApiResp<R> code(AuthRespCode respCode, String desc, R data) {
        AuthApiResp apiResp = new AuthApiResp();
        apiResp.setCode(respCode.getCode());
        apiResp.setMsg(respCode.getMsg());
        apiResp.setDesc(desc);
        apiResp.setData(data);
        return apiResp;
    }

    public static AuthApiResp error(AuthException e) {
        return code(e.getError(), e.getMessage(), e.getResp());
    }

    public static AuthApiResp error(AuthoRuntimeException e) {
        return code(e.getError(), e.getMessage(), e.getResp());
    }

    public static AuthApiResp error(AuthRespCode respCode, Exception e) {
        return code(respCode, e.getMessage());
    }

    public static AuthApiResp error(AuthRespCode respCode, String msg) {
        return code(respCode, msg);
    }
}
