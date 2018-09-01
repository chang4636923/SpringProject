package com.chang.weixinsell.entity;

import com.chang.weixinsell.enums.ProductStatusEnum;
import com.chang.weixinsell.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 食品信息
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    /**
     * 商品Id
     */
    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品缩略图
     */
    private String productIcon;

    /**
     * 商品状态，0为正常，1为下架
     */
    private Integer productStatus;

    /**
     * 类目编号
     */
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getEnumDataByCode(productStatus, ProductStatusEnum.class);
    }
}
