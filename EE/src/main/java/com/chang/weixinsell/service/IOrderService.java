package com.chang.weixinsell.service;

import com.chang.weixinsell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    /**
     * 创建订单
     *
     * @param orderDTO 新订单信息
     * @return 创建后的新订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     *
     * @param orderId 订单号
     * @return 订单信息
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     *
     * @param buyerOpenid 买家openid
     * @param pageable    分页信息
     * @return 该买家的订单列表
     */
    Page<OrderDTO> findOrderList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     *
     * @param orderDTO 要取消的订单
     * @return
     */
    OrderDTO cancleOrder(OrderDTO orderDTO);

    /**
     * 卖家接单
     *
     * @param orderDTO 接受的订单
     * @return
     */
    OrderDTO finishOrder(OrderDTO orderDTO);

    /**
     * 支付订单
     *
     * @param orderDTO 待支付的订单
     * @return
     */
    OrderDTO payOrder(OrderDTO orderDTO);

    /**
     * 查询所有订单(卖家端)
     *
     * @param pageable
     * @return
     */
    Page<OrderDTO> findList(Pageable pageable);
}
