package com.chang.weixinsell.repository;

import com.chang.weixinsell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 食品信息
 */
public interface ProductInfoReposity extends JpaRepository<ProductInfo, String> {
    /**
     * 根据商品状态一次查询所有商品
     *
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
