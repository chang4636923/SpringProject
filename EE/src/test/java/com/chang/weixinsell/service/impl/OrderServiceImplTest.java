package com.chang.weixinsell.service.impl;

import com.chang.weixinsell.dto.OrderDTO;
import com.chang.weixinsell.entity.OrderDetail;
import com.chang.weixinsell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private IOrderService service;
    private static final String buyerOpenid = "110110";
    private static final String orderId = "1533802017164909959";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("科大时代广场比特科技");
        orderDTO.setBuyerOpenid(buyerOpenid);
        orderDTO.setBuyerName("蛋哥");
        orderDTO.setBuyerPhone("15291082266");
        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(10);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123457");
        orderDetail2.setProductQuantity(10);
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = service.create(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = service.findOne(orderId);
        log.info("查询订单详情为: " + orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findOrderList() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = service.findOrderList(buyerOpenid, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancleOrder() {
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO result = service.cancleOrder(orderDTO);
        Assert.assertEquals(new Integer(2), result.getOrderStatus());
    }

    @Test
    public void finishOrder() {
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO result = service.finishOrder(orderDTO);
        Assert.assertEquals(new Integer(1), result.getOrderStatus());
    }

    @Test
    public void payOrder() {
        OrderDTO orderDTO = service.findOne(orderId);
        OrderDTO result = service.payOrder(orderDTO);
        Assert.assertEquals(new Integer(1), result.getOrderStatus());
    }

    @Test
    public void list() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> page = service.findList(request);
        Assert.assertTrue("查询所有订单列表", page.getTotalElements() > 0);
    }
}