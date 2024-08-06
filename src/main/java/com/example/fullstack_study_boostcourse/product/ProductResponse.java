package com.example.fullstack_study_boostcourse.product;

import java.util.List;

public class ProductResponse {
    private List<Product> products;
    private Integer totalCount;

    public ProductResponse(List<Product> products) {
        this.products = products;
        this.totalCount = products.size();
    }

    public ProductResponse() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
