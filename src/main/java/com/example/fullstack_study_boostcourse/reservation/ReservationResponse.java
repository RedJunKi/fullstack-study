package com.example.fullstack_study_boostcourse.reservation;

import lombok.Builder;

public class ReservationResponse {
    private Integer reservationInfoId;
    private Integer productId;
    private Integer displayInfoId;
    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private String reservationDate;
    private boolean cancelYn;
    private String createDate;
    private String modifyDate;
    private ReservationPrice prices;

    public ReservationResponse(boolean cancelYn, String createDate, Integer displayInfoId, String modifyDate, ReservationPrice prices, Integer productId, String reservationDate, String reservationEmail, Integer reservationInfoId, String reservationName, String reservationTelephone) {
        this.cancelYn = cancelYn;
        this.createDate = createDate;
        this.displayInfoId = displayInfoId;
        this.modifyDate = modifyDate;
        this.prices = prices;
        this.productId = productId;
        this.reservationDate = reservationDate;
        this.reservationEmail = reservationEmail;
        this.reservationInfoId = reservationInfoId;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTelephone;
    }

    public ReservationResponse() {
    }

    public ReservationResponse(ReservationInfo reservationInfo) {
        this.cancelYn = reservationInfo.isCancelYn();
        this.createDate = reservationInfo.getCreateDate();
        this.displayInfoId = reservationInfo.getDisplayInfoId();
        this.modifyDate = reservationInfo.getModifyDate();
        this.prices = new ReservationPrice();
        this.productId = reservationInfo.getProductId();
        this.reservationDate = reservationInfo.getReservationDate();
        this.reservationEmail = reservationInfo.getReservationEmail();
        this.reservationInfoId = reservationInfo.getReservationInfoId();
        this.reservationName = reservationInfo.getReservationName();
        this.reservationTelephone = reservationInfo.getReservationTelephone();
    }

    public boolean isCancelYn() {
        return cancelYn;
    }

    public void setCancelYn(boolean cancelYn) {
        this.cancelYn = cancelYn;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getDisplayInfoId() {
        return displayInfoId;
    }

    public void setDisplayInfoId(Integer displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public ReservationPrice getPrices() {
        return prices;
    }

    public void setPrices(ReservationPrice prices) {
        this.prices = prices;
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
}
