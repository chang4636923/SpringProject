package com.chang.weixinsell.exception;

import com.chang.weixinsell.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class ProductStatusError extends RuntimeException {
    private Integer code;

    public ProductStatusError(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }
}
