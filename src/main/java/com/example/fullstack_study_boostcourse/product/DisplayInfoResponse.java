package com.example.fullstack_study_boostcourse.product;

import com.example.fullstack_study_boostcourse.reservation.Comment;
import com.example.fullstack_study_boostcourse.reservation.DisplayInfo;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class DisplayInfoResponse {
    private BigDecimal averageScore;
    private List<Comment> comments;
    private DisplayInfo displayInfo;
    private DisplayInfoImage displayInfoImage;
    private List<ProductImage> productImages;
    private List<ProductPrice> productPrices;

    public DisplayInfoResponse() {
    }

    public DisplayInfoResponse(BigDecimal averageScore, List<Comment> comments, DisplayInfo displayInfo, DisplayInfoImage displayInfoImage, List<ProductImage> productImages, List<ProductPrice> productPrices) {
        this.averageScore = averageScore;
        this.comments = comments;
        this.displayInfo = displayInfo;
        this.displayInfoImage = displayInfoImage;
        this.productImages = productImages;
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