package com.chang.weixinsell.exception;

import com.chang.weixinsell.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class ProductStockErrorException extends RuntimeException {
    private Integer code;

    public ProductStockErrorException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }
}
