package com.example.fullstack_study_boostcourse.reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public List<ReservationInfo> getReservationByEmail(String email) {
        return reservationDao.selectByEmail(email);
    }

    @Override
    public ReservationResponse addReservation(ReservationParam param) {


        ReservationInfo result = ReservationInfo.builder()
                .productId(param.getProductId())
                .displayInfoId(param.getDisplayInfoId())
                .reservationName(param.getReservationName())
                .reservationTelephone(param.getReservationTelephone())
                .reservationEmail(param.getReservationEmail())
                .cancelYn(false)
                .reservationDate(param.getReservationYearMonthDay())
                .createDate(getCurrentDate())
                .modifyDate(getCurrentDate())
                .build();

        Long id = reservationDao.insertReservation(result);

        result.setReservationInfoId(id.intValue());


        return new ReservationResponse(result);
    }

    private static String getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    @Override
    public ReservationResponse cancelReservation(int reservationId) {
        ReservationInfo reservationInfo = reservationDao.selectByReservationId(reservationId);
        reservationInfo.setCancelYn(true);
        reservationDao.updateReservation(reservationInfo);

        return new ReservationResponse(reservationInfo);
    }

    @Override
    public CommentResponse addCommentToReservation(int reservationInfoId, int productId, String comment, BigDecimal score) {
        String dateTimeNow = getCurrentDate();

        Comment commentBuild = Comment.builder()
                .productId(productId)
                .reservationInfoId(reservationInfoId)
                .score(score)
                .comment(comment)
                .createDate(dateTimeNow)
                .modifyDate(dateTimeNow)
                .build();

        Long id = reservationDao.insertComment(commentBuild);

        return CommentResponse.builder()
                .commentId(id.intValue())
                .productId(productId)
                .reservationInfoId(reservationInfoId)
                .score(score)
                .comment(comment)
                .createDate(dateTimeNow)
                .modifyDate(dateTimeNow)
                .commentImage(null)
                .build();
    }
}
