package com.chang.weixinsell.repository;

import com.chang.weixinsell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailReposityTest {
    @Autowired
    private OrderDetailReposity reposity;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("豆腐脑");
        orderDetail.setProductPrice(new BigDecimal(3.5));
        orderDetail.setProductQuantity(100);

        OrderDetail result = reposity.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = reposity.findByOrderId("123456");
        Assert.assertEquals(2, result.size());
    }
}