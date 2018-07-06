package com.chang.common;

import lombok.Data;

@Data
public class Page {
    private Integer pageSize = 10;

    private Integer pageNumber = 1;

    private Integer pageOffset = (this.getPageNumber() - 1) * this.getPageSize();

    private String orderColumnName;

    public Integer getPageOffset() {
        this.pageOffset =
                (this.getPageNumber() - 1) * this.getPageSize();
        return this.pageOffset;
    }
}
