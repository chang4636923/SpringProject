package com.chang.weixinsell.service;

import com.chang.weixinsell.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * 商品类目表Service定义
 */
public interface IProductCategoryService {
    Optional<ProductCategory> findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
