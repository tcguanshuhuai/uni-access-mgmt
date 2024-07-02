package com.sguan.bg.authsvc.globalhandler;


import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.sguan.bg.authsvc.annotations.ResultWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMessage;
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
    public AuthApiResp handleBadReqException(HttpServletResponse response, Exception e) {
        log.error("client bad request: " + e.getMessage(), e);
        return AuthApiResp.error(AuthRespCode.CLI_BAD_REQ, e);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AuthApiResp handleBindException(HttpServletResponse response, BindException e) {
        log.error("client input error: " + e.getMessage(), e);
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return AuthApiResp.error(AuthRespCode.CLI_INVALID_INPUT, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AuthApiResp handleMANVException(HttpServletResponse response, MethodArgumentNotValidException e) {
        log.error("client input error: " + e.getMessage(), e);
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return AuthApiResp.error(AuthRespCode.CLI_INVALID_INPUT, message);
    }


    /**
     *==========================================================================
     *=           This Exception should be threw in controller layer           =
     *==========================================================================
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AuthApiResp handleAuthException(HttpServletResponse response, AuthException e) {
        log.error("sever internal error: " + e.getMessage(), e);
        return AuthApiResp.error(e);
    }

    /**
     *================================================================
     *=           This Exception can be threw in any layer           =
     *================================================================
     */
    @ExceptionHandler(AuthoRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AuthApiResp handleAuthException(HttpServletResponse response, AuthoRuntimeException e) {
        log.error("sever internal error: " + e.getMessage(), e);
        return AuthApiResp.error(e);
    }

    /**
     *================================================
     *=           System unknown Exception           =
     *================================================
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AuthApiResp handleAuthException(HttpServletResponse response, Exception e) {
        log.error("sever internal error: " + e.getMessage(), e);
        return AuthApiResp.error(AuthRespCode.SEVER_ERROR, e);
    }
}
