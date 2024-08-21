package com.example.fullstack_study.product.domain;

import com.example.fullstack_study.reservation.domain.Comment;
import com.example.fullstack_study.reservation.domain.DisplayInfo;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class DisplayInfoResponse {
    private DisplayInfo displayInfo;
    private List<ProductImage> productImages;
    private DisplayInfoImage displayInfoImage;
    private List<Comment> comments;
    private BigDecimal averageScore;
    private List<ProductPrice> productPrices;

    public DisplayInfoResponse() {
    }

    public DisplayInfoResponse(DisplayInfo displayInfo, List<ProductImage> productImages, DisplayInfoImage displayInfoImage, List<Comment> comments, BigDecimal averageScore, List<ProductPrice> productPrices) {
        this.displayInfo = displayInfo;
        this.productImages = productImages;
        this.displayInfoImage = displayInfoImage;
        this.comments = comments;
        this.averageScore = averageScore;
        this.productPrices = productPrices;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public DisplayInfo getDisplayInfo() {
        return displayInfo;
    }

    public void setDisplayInfo(DisplayInfo displayInfo) {
        this.displayInfo = displayInfo;
    }

    public DisplayInfoImage getDisplayInfoImage() {
        return displayInfoImage;
    }

    public void setDisplayInfoImage(DisplayInfoImage displayInfoImage) {
        this.displayInfoImage = displayInfoImage;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<ProductPrice> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(List<ProductPrice> productPrices) {
        this.productPrices = productPrices;
    }
}
