package com.chang.weixinsell.controller;

import com.chang.weixinsell.VO.ResultVO;
import com.chang.weixinsell.dto.OrderDTO;
import com.chang.weixinsell.enums.ExceptionEnum;
import com.chang.weixinsell.exception.OrderException;
import com.chang.weixinsell.form.OrderDetailForm;
import com.chang.weixinsell.form.OrderForm;
import com.chang.weixinsell.form.OrderListForm;
import com.chang.weixinsell.service.IBuyerService;
import com.chang.weixinsell.service.IOrderService;
import com.chang.weixinsell.util.OrderForm2OrderDTO;
import com.chang.weixinsell.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IBuyerService buyerService;

    /**
     * 创建订单Controller
     *
     * @param orderForm     表单入参校验
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        // 创建订单入参有误
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,orderForm={}", orderForm);
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // 将OrderForm转为OrderDTO
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new OrderException(ExceptionEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 查询订单列表Controller
     *
     * @param orderListForm 表单入参校验
     * @param bindingResult
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@Valid OrderListForm orderListForm,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【查询订单列表】失败，openid为空");
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        PageRequest request = PageRequest.of(orderListForm.getPage(), orderListForm.getSize());
        Page<OrderDTO> orderDTOPage = orderService.findOrderList(
                orderListForm.getOpenid(), request);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 查询订单详情controller
     *
     * @param orderDetailForm 订单详情表单校验
     * @param bindingResult
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@Valid OrderDetailForm orderDetailForm,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【查询订单详情】失败，参数不正确,orderDetailForm={}", orderDetailForm);
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO result = buyerService.findOneOrder(orderDetailForm.getOpenid(),
                orderDetailForm.getOrderId());
        return ResultVOUtil.success(result);
    }

    /**
     * 取消订单controller
     *
     * @param orderDetailForm
     * @param bindingResult
     * @return
     */
    @GetMapping("/cancle")
    public ResultVO<OrderDTO> cancle(@Valid OrderDetailForm orderDetailForm,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【取消订单】失败，参数不正确,orderDetailForm={}", orderDetailForm);
            throw new OrderException(ExceptionEnum.ORDER_FORM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO result = buyerService.cancleOrder(orderDetailForm.getOpenid(),
                orderDetailForm.getOrderId());
        return ResultVOUtil.success(result);
    }
}
