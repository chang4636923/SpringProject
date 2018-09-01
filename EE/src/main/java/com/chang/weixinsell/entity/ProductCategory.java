package com.chang.weixinsell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 买家类目实体类
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 类目Id
     */
    private Integer categoryId;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 类目类型
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
