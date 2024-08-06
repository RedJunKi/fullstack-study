package com.example.fullstack_study_boostcourse.promotion;

public class Promotion {
    private Integer id;
    private int productId;
    private String productImageUrl;

    public Promotion() {
    }

    public Promotion(Integer id, int productId, String productImageUrl) {
        this.id = id;
        this.productId = productId;
        this.productImageUrl = productImageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
}
