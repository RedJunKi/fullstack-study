package com.example.fullstack_study_boostcourse.reservation;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class Comment {
    private String comment;
    private Integer commentId;
    private List<CommentImage> commentImages;
    private String createDate;
    private String modifyDate;
    private Integer productId;
    private String reservationDate;
    private String reservationEmail;
    private Integer reservationInfoId;
    private String reservationName;
    private String reservationTelephone;
    private BigDecimal score;

    public Comment() {
    }

    public Comment(String comment, Integer commentId, List<CommentImage> commentImages, String createDate, String modifyDate, Integer productId, String reservationDate, String reservationEmail, Integer reservationInfoId, String reservationName, String reservationTelephone, BigDecimal score) {
        this.comment = comment;
        this.commentId = commentId;
        this.commentImages = commentImages;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.productId = productId;
        this.reservationDate = reservationDate;
        this.reservationEmail = reservationEmail;
        this.reservationInfoId = reservationInfoId;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTelephone;
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

    public List<CommentImage> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(List<CommentImage> commentImages) {
        this.commentImages = commentImages;
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

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationEmail() {
        return reservationEmail;
    }

    public void setReservationEmail(String reservationEmail) {
        this.reservationEmail = reservationEmail;
    }

    public Integer getReservationInfoId() {
        return reservationInfoId;
    }

    public void setReservationInfoId(Integer reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public String getReservationTelephone() {
        return reservationTelephone;
    }

    public void setReservationTelephone(String reservationTelephone) {
        this.reservationTelephone = reservationTelephone;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
