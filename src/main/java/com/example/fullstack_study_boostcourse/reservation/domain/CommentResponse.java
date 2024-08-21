package com.example.fullstack_study_boostcourse.reservation.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class CommentResponse {

    private String comment;
    private Integer commentId;
    private CommentImage commentImage;
    private String createDate;
    private String modifyDate;
    private Integer productId;
    private Integer reservationInfoId;
    private BigDecimal score;

    public CommentResponse() {
    }

    public CommentResponse(String comment, Integer commentId, CommentImage commentImage, String createDate, String modifyDate, Integer productId, Integer reservationInfoId, BigDecimal score) {
        this.comment = comment;
        this.commentId = commentId;
        this.commentImage = commentImage;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.productId = productId;
        this.reservationInfoId = reservationInfoId;
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public CommentImage getCommentImage() {
        return commentImage;
    }

    public void setCommentImage(CommentImage commentImage) {
        this.commentImage = commentImage;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getReservationInfoId() {
        return reservationInfoId;
    }

    public void setReservationInfoId(Integer reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
