package com.chang.weixinsell.service;

import com.chang.weixinsell.dto.CartDTO;
import com.chang.weixinsell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IProductService {
    Optional<ProductInfo> findOne(String productId);

    /**
     * 查询所有上架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);

    ProductInfo onSale(String productId);

    ProductInfo offSale(String productId);
}
