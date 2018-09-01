package com.chang.weixinsell.service;

import com.chang.weixinsell.dto.OrderDTO;

/**
 * 买家service
 */
public interface IBuyerService {
    OrderDTO findOneOrder(String openid, String orderId);

    OrderDTO cancleOrder(String openid, String orderId);
}
