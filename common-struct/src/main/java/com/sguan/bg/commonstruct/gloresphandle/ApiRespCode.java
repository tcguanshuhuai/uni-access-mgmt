package com.sguan.bg.commonstruct.gloresphandle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiRespCode {

    /**
     * Success
     */
    SUCCESS(0, "OK"),

    /**
     *=======================================================
     *=           business error code client error          =
     *=======================================================
     */
    CLI_INVALID_INPUT(11000, "Client Invalid Input"),
    CLI_BAD_REQ(11001, "Client Bad Request"),

    /**
     *======================================================
     *=           business error code sever error          =
     *======================================================
     */
    SEVER_ERROR(21000, "Sever Error")
    ;
    private final int code;
    private final String msg;

}
