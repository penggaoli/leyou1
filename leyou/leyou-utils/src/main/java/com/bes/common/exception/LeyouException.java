package com.bes.common.exception;

import com.bes.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LeyouException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
