package com.chang.weixinsell.repository;

import com.chang.weixinsell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 主订单
 */

public interface OrderMasterReposity extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
