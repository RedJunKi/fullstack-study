package com.example.fullstack_study_boostcourse.reservation.domain;

import java.util.List;

public class ReservationParam {
    private Integer displayInfoId;
    private List<ReservationPrice> prices;
    private Integer productId;
    private String reservationEmail;
    private String reservationName;
    private String reservationTelephone;
    private String reservationYearMonthDay;

    public ReservationParam() {
    }

    public ReservationParam(Integer displayInfoId, List<ReservationPrice> prices, Integer productId, String reservationEmail, String reservationName, String reservationTelephone, String reservationYearMonthDay) {
        this.displayInfoId = displayInfoId;
        this.prices = prices;
        this.productId = productId;
        this.reservationEmail = reservationEmail;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTelephone;
        this.reservationYearMonthDay = reservationYearMonthDay;
    }

    public Integer getDisplayInfoId() {
        return displayInfoId;
    }

    public void setDisplayInfoId(Integer displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    public List<ReservationPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ReservationPrice> prices) {
        this.prices = prices;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getReservationEmail() {
        return reservationEmail;
    }

    public void setReservationEmail(String reservationEmail) {
        this.reservationEmail = reservationEmail;
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

    public String getReservationYearMonthDay() {
        return reservationYearMonthDay;
    }

    public void setReservationYearMonthDay(String reservationYearMonthDay) {
        this.reservationYearMonthDay = reservationYearMonthDay;
    }
}
