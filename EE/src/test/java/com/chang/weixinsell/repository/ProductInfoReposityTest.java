package com.chang.weixinsell.repository;

import com.chang.weixinsell.entity.ProductInfo;
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
public class ProductInfoReposityTest {
    @Autowired
    private ProductInfoReposity reposity;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("胡辣汤");
        productInfo.setProductPrice(new BigDecimal(3.5));
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("正宗河南胡辣汤");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        ProductInfo result = reposity.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = reposity.findByProductStatus(0);
        Assert.assertNotEquals(0, result.size());
    }
}