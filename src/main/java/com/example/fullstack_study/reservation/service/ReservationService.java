package com.example.fullstack_study.reservation.service;

import com.example.fullstack_study.reservation.domain.CommentResponse;
import com.example.fullstack_study.reservation.domain.ReservationInfo;
import com.example.fullstack_study.reservation.domain.ReservationParam;
import com.example.fullstack_study.reservation.domain.ReservationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ReservationService {
    List<ReservationInfo> getReservationByEmail(String email);

    ReservationResponse addReservation(ReservationParam param);

    ReservationResponse cancelReservation(int reservationId);

    CommentResponse addCommentToReservation(int reservationInfoId, int productId, String comment, BigDecimal score);

    void addCommentImageFile(int reservationInfoId, int commentId, MultipartFile file);
}
