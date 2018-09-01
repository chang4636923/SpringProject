package com.chang.weixinsell.repository;

import com.chang.weixinsell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryReposityTest {
    @Autowired
    private ProductCategoryReposity reposity;

    @Test
    public void findOneTest() {
        Optional<ProductCategory> productCategory = reposity.findById(1);

        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("女生最爱", 4);
        ProductCategory result = reposity.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 3, 4);
        List<ProductCategory> result =
                reposity.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}