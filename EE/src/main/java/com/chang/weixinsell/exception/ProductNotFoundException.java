package com.chang.weixinsell.exception;

import com.chang.weixinsell.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private Integer code;

    public ProductNotFoundException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }
}
