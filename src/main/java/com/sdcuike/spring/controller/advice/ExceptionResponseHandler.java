package com.sdcuike.spring.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 *
 * @author sdcuike
 *         <p>
 *         Created on 2017-02-09 11:34:49<br>
 *         <p>
 *         {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler}
 */
@RestControllerAdvice
@Slf4j
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {
    
    /**
     * 应用异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleSysException(Exception ex, WebRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
        log.error("handleException:{}", result, ex);
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
//    /**
//     * spring mvc 内部异常处理ˇ
//     */
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
//            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
//        }
//
//        Map<String, Object> result = new HashMap<>();
//        result.put(status.toString(), ex.getMessage());
//        result.put("body", body);
//
//        log.error("handleExceptionInternal:{}", result, ex);
//        return new ResponseEntity<Object>(result, headers, status);
//    }
    
}
