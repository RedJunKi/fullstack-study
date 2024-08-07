package com.example.fullstack_study_boostcourse.reservation;

public class ReservationPrice {
    private Integer reservationInfoPriceId;
    private Integer reservationInfoId;
    private Integer productPriceId;
    private Integer count;

    public ReservationPrice(Integer count, Integer productPriceId, Integer reservationInfoId, Integer reservationInfoPriceId) {
        this.count = count;
        this.productPriceId = productPriceId;
        this.reservationInfoId = reservationInfoId;
        this.reservationInfoPriceId = reservationInfoPriceId;
    }

    public ReservationPrice() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getProductPriceId() {
        return productPriceId;
    }

    public void setProductPriceId(Integer productPriceId) {
        this.productPriceId = productPriceId;
    }

    public Integer getReservationInfoId() {
        return reservationInfoId;
    }

    public void setReservationInfoId(Integer reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public Integer getReservationInfoPriceId() {
        return reservationInfoPriceId;
    }

    public void setReservationInfoPriceId(Integer reservationInfoPriceId) {
        this.reservationInfoPriceId = reservationInfoPriceId;
    }
}
