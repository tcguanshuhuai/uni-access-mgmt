package com.sguan.bg.commonstruct.gloresphandle;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private ApiRespCode error;

    @Getter
    private Object resp;

    public ApiRuntimeException() {
        this(ApiRespCode.SEVER_ERROR);

    }

    public ApiRuntimeException(ApiRespCode error) {
        super();
        this.error = error;
    }


    public ApiRuntimeException(ApiRespCode error, Throwable e) {
        super(e);
        this.error = error;
    }

    public ApiRuntimeException(ApiRespCode error, String msg) {
        super(msg);
        this.error = error;
    }


    public ApiRuntimeException(ApiRespCode error, Object resp, Throwable e) {
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
