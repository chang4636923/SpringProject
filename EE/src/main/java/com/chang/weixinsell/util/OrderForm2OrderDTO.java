package com.chang.weixinsell.util;

import com.chang.weixinsell.dto.OrderDTO;
import com.chang.weixinsell.entity.OrderDetail;
import com.chang.weixinsell.enums.ExceptionEnum;
import com.chang.weixinsell.exception.OrderException;
import com.chang.weixinsell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        Gson gson = new Gson();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误,string={}", orderForm.getItems());
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
