package com.example.fullstack_study_boostcourse.reservation;

import java.math.BigDecimal;
import java.util.List;

public interface ReservationService {
    List<ReservationInfo> getReservationByEmail(String email);

    ReservationResponse addReservation(ReservationParam param);

    ReservationResponse deleteReservation(int reservationId);

    CommentResponse addCommentToReservation(int reservationInfoId, int productId, String comment, BigDecimal score);
}
