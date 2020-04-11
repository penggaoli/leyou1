package com.bes.common.advice;

import com.bes.common.enums.ExceptionEnum;
import com.bes.common.exception.LeyouException;
import com.bes.common.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(LeyouException.class)
    public ResponseEntity<RestException> handlerException(LeyouException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new RestException(e.getExceptionEnum()));
    }
}
