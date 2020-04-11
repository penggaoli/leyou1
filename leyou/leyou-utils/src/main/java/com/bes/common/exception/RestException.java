package com.bes.common.exception;

import com.bes.common.enums.ExceptionEnum;
import lombok.Data;

@Data
public class RestException {
    private Integer status;
    private String message;
    private Long timestamp;

    public RestException(ExceptionEnum e) {
        this.status = e.getCode();
        this.message = e.getMessage();
        this.timestamp = System.currentTimeMillis();
    }
}
