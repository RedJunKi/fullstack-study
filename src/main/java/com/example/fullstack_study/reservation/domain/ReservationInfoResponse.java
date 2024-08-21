package com.example.fullstack_study.reservation.domain;

import java.util.List;

public class ReservationInfoResponse {
    private List<ReservationInfo> reservations;
    private int size;

    public ReservationInfoResponse(List<ReservationInfo> reservations) {
        this.reservations = reservations;
        this.size = reservations.size();
    }

    public List<ReservationInfo> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationInfo> reservations) {
        this.reservations = reservations;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
