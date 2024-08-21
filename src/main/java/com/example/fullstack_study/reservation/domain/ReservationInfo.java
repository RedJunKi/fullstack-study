package com.example.fullstack_study.reservation.domain;

import lombok.Builder;

@Builder
public class ReservationInfo {
    private Integer reservationInfoId;
    private Integer productId;
    private Integer displayInfoId;
    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private boolean cancelYn;
    private String reservationDate;
    private String createDate;
    private String modifyDate;
    private DisplayInfo displayInfo;
    private Integer totalPrice;

    public ReservationInfo() {
    }

    public ReservationInfo(Integer reservationInfoId, Integer productId, Integer displayInfoId, String reservationName, String reservationTelephone, String reservationEmail, boolean cancelYn, String reservationDate, String createDate, String modifyDate, DisplayInfo displayInfo, Integer totalPrice) {
        this.reservationInfoId = reservationInfoId;
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTelephone;
        this.reservationEmail = reservationEmail;
        this.cancelYn = cancelYn;
        this.reservationDate = reservationDate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.displayInfo = displayInfo;
        this.totalPrice = totalPrice;
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

    public DisplayInfo getDisplayInfo() {
        return displayInfo;
    }

    public void setDisplayInfo(DisplayInfo displayInfo) {
        this.displayInfo = displayInfo;
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

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
