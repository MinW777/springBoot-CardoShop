package com.min.cardshop.dto;

import com.min.cardshop.constant.ProductCategory;

public class ProductParam {
    ProductCategory category;
    String keyword;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
