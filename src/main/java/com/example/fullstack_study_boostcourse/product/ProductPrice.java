package com.example.fullstack_study_boostcourse.product;

import java.math.BigDecimal;

public class ProductPrice {
    private String createDate;
    private BigDecimal discountRate;
    private String modifyDate;
    private Integer price;
    private String priceTypeName;
    private Integer productId;
    private Integer productPriceId;

    public ProductPrice(String createDate, BigDecimal discountRate, String modifyDate, Integer price, String priceTypeName, Integer productId, Integer productPriceId) {
        this.createDate = createDate;
        this.discountRate = discountRate;
        this.modifyDate = modifyDate;
        this.price = price;
        this.priceTypeName = priceTypeName;
        this.productId = productId;
        this.productPriceId = productPriceId;
    }

    public ProductPrice() {
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPriceTypeName() {
        return priceTypeName;
    }

    public void setPriceTypeName(String priceTypeName) {
        this.priceTypeName = priceTypeName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductPriceId() {
        return productPriceId;
    }

    public void setProductPriceId(Integer productPriceId) {
        this.productPriceId = productPriceId;
    }
}
