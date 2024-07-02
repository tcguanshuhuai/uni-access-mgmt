package com.sguan.bg.commonstruct.gloresphandle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sguan.bg.commonstruct.annotations.ResultWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = methodParameter.getMethod();
        Class<?> klass = methodParameter.getDeclaringClass();
        return method.isAnnotationPresent(ResultWrapper.class) || klass.isAnnotationPresent(ResultWrapper.class);

    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (null == body) {
            return ApiResp.ok();
        }
        if (body instanceof String) {
            return objectMapper.writeValueAsString(ApiResp.ok(body));
        }
        return ApiResp.ok(body);
    }
}
