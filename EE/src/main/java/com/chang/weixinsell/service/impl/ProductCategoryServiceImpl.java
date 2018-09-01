package com.chang.weixinsell.service.impl;

import com.chang.weixinsell.entity.ProductCategory;
import com.chang.weixinsell.repository.ProductCategoryReposity;
import com.chang.weixinsell.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 商品类目表Service实现类
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
    @Autowired
    private ProductCategoryReposity reposity;

    @Override
    public Optional<ProductCategory> findOne(Integer categoryId) {
        return reposity.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return reposity.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return reposity.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return reposity.save(productCategory);
    }
}
