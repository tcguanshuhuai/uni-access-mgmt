package com.sguan.bg.commonstruct.gloresphandle;


import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            HttpMessageNotReadableException.class,
            MismatchedInputException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResp handleBadReqException(HttpServletResponse response, Exception e) {
        log.error("client bad request: " + e.getMessage(), e);
        return ApiResp.error(ApiRespCode.CLI_BAD_REQ, e);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResp handleBindException(HttpServletResponse response, BindException e) {
        log.error("client input error: " + e.getMessage(), e);
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return ApiResp.error(ApiRespCode.CLI_INVALID_INPUT, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResp handleMANVException(HttpServletResponse response, MethodArgumentNotValidException e) {
        log.error("client input error: " + e.getMessage(), e);
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return ApiResp.error(ApiRespCode.CLI_INVALID_INPUT, message);
    }


    /**
     *==========================================================================
     *=           This Exception should be threw in controller layer           =
     *==========================================================================
     */
    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResp handleApiException(HttpServletResponse response, ApiException e) {
        log.error("sever internal error: " + e.getMessage(), e);
        return ApiResp.error(e);
    }

    /**
     *================================================================
     *=           This Exception can be threw in any layer           =
     *================================================================
     */
    @ExceptionHandler(ApiRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResp handleApiException(HttpServletResponse response, ApiRuntimeException e) {
        log.error("sever internal error: " + e.getMessage(), e);
        return ApiResp.error(e);
    }

    /**
     *================================================
     *=           System unknown Exception           =
     *================================================
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResp handleApiException(HttpServletResponse response, Exception e) {
        log.error("sever internal error: " + e.getMessage(), e);
        return ApiResp.error(ApiRespCode.SEVER_ERROR, e);
    }
}
