package com.sguan.bg.commonstruct.gloresphandle;

import lombok.Data;

@Data
public class ApiResp<T> {
    private int code;
    private String msg;
    private String desc;
    private T data;



    public static ApiResp ok() {
        return code(ApiRespCode.SUCCESS, "", null);
    }

    public static <R> ApiResp<R> ok(R data) {
        return code(ApiRespCode.SUCCESS, "", data);
    }

    public static <R> ApiResp<R> ok(String desc, R data) {
        return code(ApiRespCode.SUCCESS, "", data);
    }

    public static ApiResp code(ApiRespCode respCode) {
        return code(respCode, "", null);
    }

    public static ApiResp code(ApiRespCode respCode, String desc) {
        return code(respCode, desc, null);
    }

    public static <R> ApiResp<R> code(ApiRespCode respCode, R data) {
        return code(respCode, "", data);
    }

    public static <R> ApiResp<R> code(ApiRespCode respCode, String desc, R data) {
        ApiResp apiResp = new ApiResp();
        apiResp.setCode(respCode.getCode());
        apiResp.setMsg(respCode.getMsg());
        apiResp.setDesc(desc);
        apiResp.setData(data);
        return apiResp;
    }

    public static ApiResp error(ApiException e) {
        return code(e.getError(), e.getMessage(), e.getResp());
    }

    public static ApiResp error(ApiRuntimeException e) {
        return code(e.getError(), e.getMessage(), e.getResp());
    }

    public static ApiResp error(ApiRespCode respCode, Exception e) {
        return code(respCode, e.getMessage());
    }

    public static ApiResp error(ApiRespCode respCode, String msg) {
        return code(respCode, msg);
    }
}
