package com.chang.weixinsell.repository;

import com.chang.weixinsell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 食品目录
 */
public interface ProductCategoryReposity extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
