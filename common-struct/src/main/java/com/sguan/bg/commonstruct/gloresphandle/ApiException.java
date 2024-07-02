package com.sguan.bg.commonstruct.gloresphandle;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiException extends Exception {
    private static final long serialVersionUID = 1L;

    @Getter
    private ApiRespCode error;

    @Getter
    private Object resp;

    public ApiException() {
        this(ApiRespCode.SEVER_ERROR);

    }

    public ApiException(ApiRespCode error) {
        super();
        this.error = error;
    }


    public ApiException(ApiRespCode error, Throwable e) {
        super(e);
        this.error = error;
    }

    public ApiException(ApiRespCode error, String msg) {
        super(msg);
        this.error = error;
    }


    public ApiException(ApiRespCode error, Object resp, Throwable e) {
        super(e);
        this.error = error;
        this.resp = resp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("error", error)
                .append("msg", this.getMessage())
                .append("resp", resp).toString();
    }

}
