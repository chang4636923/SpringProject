package com.chang.weixinsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单详情
 */
@Data
public class OrderDetailForm {
    @NotEmpty(message = "用户openid不能为空")
    private String openid;
    @NotEmpty(message = "用户订单Id不能为空")
    private String orderId;
}
