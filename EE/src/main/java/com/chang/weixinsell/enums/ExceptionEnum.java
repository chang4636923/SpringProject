package com.chang.weixinsell.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    ORDER_FORM_ERROR(1, "订单表单入参有误"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不足"),
    ORDER_NOT_EXISTS(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXISTS(13, "未找到此订单详情"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_NO_DETAIL(16, "订单中无商品详情"),
    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),
    CART_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户!"),
    WECHAT_MP_ERROR(20, "微信公众号授权错误"),
    PRODUCT_STATUS_ERROR(21, "商品状态错误");;
    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
