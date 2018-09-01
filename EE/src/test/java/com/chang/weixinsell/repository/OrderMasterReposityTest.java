package com.chang.weixinsell.repository;

import com.chang.weixinsell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterReposityTest {
    @Autowired
    private OrderMasterReposity reposity;

    private final String OPENID = "120110";

    @Test
    public void savaTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("蛋哥");
        orderMaster.setBuyerPhone("15291082266");
        orderMaster.setBuyerAddress("比特科技");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = reposity.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0, 1);
        Page<OrderMaster> result = reposity.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}