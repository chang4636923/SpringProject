package com.chang.weixinsell.service.impl;

import com.chang.weixinsell.entity.ProductCategory;
import com.chang.weixinsell.service.IProductCategoryService;
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
public class ProductCategoryServiceTest {
    @Autowired
    private IProductCategoryService productCategoryService;

    @Test
    public void findOne() {
        Optional<ProductCategory> productCategory
                = productCategoryService.findOne(1);
        Assert.assertNotNull(productCategory.get());
    }

    @Test
    public void findAll() {
        List<ProductCategory> result = productCategoryService.findAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(2, 3, 4);
        List<ProductCategory> result
                = productCategoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory productCategory = new ProductCategory("我的最爱", 5);
        ProductCategory result = productCategoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}