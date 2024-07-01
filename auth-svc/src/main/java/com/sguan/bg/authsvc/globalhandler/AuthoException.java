package com.sguan.bg.authsvc.globalhandler;

import lombok.Getter;

public class AuthoException extends Exception {
    private static final long serialVersionUID = 1L;

    @Getter
    private AuthRespCode error;

    @Getter
    private Object resp;





}
