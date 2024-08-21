package com.example.fullstack_study.reservation.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class Comment {
    private Integer commentId;
    private Integer productId;
    private Integer reservationInfoId;
    private BigDecimal score;
    private String comment;
    private String reservationName;
    private String reservationEmail;
    private String reservationTelephone;
    private String reservationDate;
    private String createDate;
    private String modifyDate;
    private List<CommentImage> commentImages;

    public Comment() {
    }

    public Comment(Integer commentId, Integer productId, Integer reservationInfoId, BigDecimal score, String comment, String reservationName, String reservationEmail, String reservationTelephone, String reservationDate, String createDate, String modifyDate, List<CommentImage> commentImages) {
        this.commentId = commentId;
        this.productId = productId;
        this.reservationInfoId = reservationInfoId;
        this.score = score;
        this.comment = comment;
        this.reservationName = reservationName;
        this.reservationEmail = reservationEmail;
        this.reservationTelephone = reservationTelephone;
        this.reservationDate = reservationDate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.commentImages = commentImages;
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
