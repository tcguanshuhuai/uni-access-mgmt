package com.sguan.bg.authsvc.globalhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthRespCode {

    /**
     * Success
     */
    SUCCESS(0, "Ok"),

    /**
     *=======================================================
     *=           business error code client error          =
     *=======================================================
     */
    CLI_INVALID_INPUT(41000, "Client Invalid Input"),
    CLI_BAD_REQ(41001, "Client Bad Request"),

    /**
     *======================================================
     *=           business error code sever error          =
     *======================================================
     */
    SEVER_ERROR(51000, "Sever Error")
    ;
    private final int value;
    private final String msg;

}
