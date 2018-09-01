package com.chang.weixinsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情实体
 */
@Data
@Entity
public class OrderDetail {
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    /**
     * 商品数量
     */
    private Integer productQuantity;
    /**
     * 商品小图
     */
    private String productIcon;
}
