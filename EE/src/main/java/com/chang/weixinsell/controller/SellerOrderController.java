package com.chang.weixinsell.controller;

import com.chang.weixinsell.dto.OrderDTO;
import com.chang.weixinsell.exception.OrderException;
import com.chang.weixinsell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家订单详情页
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 卖家端查看所有订单
     *
     * @param page 查看第几页订单
     * @param size 每页显示的订单数量
     * @param map  返回给前端的参数
     * @return 订单信息页
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);
    }

    /**
     * 卖家端取消订单
     *
     * @param orderId 要取消的订单Id
     * @param map     返回给前端参数
     * @return 取消是否成功页面
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancleOrder(orderDTO);
        } catch (OrderException e) {
            log.error("【卖家端取消订单】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("/comm/error", map);
        }
        map.put("msg", "订单取消成功");
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("/comm/success", map);
    }

    /**
     * 卖家端查看订单详情
     *
     * @param orderId 要查询的订单
     * @param map     传递给前端参数
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (OrderException e) {
            log.error("【卖家端订单详情】异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("/comm/error", map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("/order/detail", map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        try {
            orderService.finishOrder(orderDTO);
        } catch (OrderException e) {
            log.error("【完结订单】异常,{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("/comm/error");
        }
        map.put("msg", "订单完结成功");
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("/comm/success", map);
    }

}
