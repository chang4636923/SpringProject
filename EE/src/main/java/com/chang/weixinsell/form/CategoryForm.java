package com.chang.weixinsell.form;

import lombok.Data;

/**
 * 卖家端类目表单
 */
@Data
public class CategoryForm {
    private String categoryName;
    private Integer categoryType;
    private Integer categoryId;
}
