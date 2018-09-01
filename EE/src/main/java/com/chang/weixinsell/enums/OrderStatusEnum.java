package com.chang.weixinsell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements IEnumCode {
    NEW(0, "新订单"),
    FINISHED(1, "订单完成"),
    CANCEL(2, "取消订单");
    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
