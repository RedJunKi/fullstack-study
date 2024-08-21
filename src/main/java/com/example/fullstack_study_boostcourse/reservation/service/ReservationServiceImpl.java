package com.example.fullstack_study_boostcourse.reservation.service;

import com.example.fullstack_study_boostcourse.product.repository.ProductDao;
import com.example.fullstack_study_boostcourse.reservation.domain.*;
import com.example.fullstack_study_boostcourse.reservation.repository.ReservationDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final String FILE_SAVE_ROUTE = "C:\\Users\\bing8\\Practice\\fullstack-study\\src\\main\\webapp\\img\\";
    private final String FILE_SAVE_ROUTE_FOLDER = "img/";

    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ReservationInfo> getReservationByEmail(String email) {
        return reservationDao.selectByEmail(email);
    }

    @Override
    public ReservationResponse addReservation(ReservationParam param) {
        int totalPrice = getTotalPrice(param);

        ReservationInfo result = ReservationInfo.builder()
                .productId(param.getProductId())
                .displayInfoId(param.getDisplayInfoId())
                .reservationName(param.getReservationName())
                .reservationTelephone(param.getReservationTelephone())
                .reservationEmail(param.getReservationEmail())
                .cancelYn(false)
                .totalPrice(totalPrice)
                .reservationDate(param.getReservationYearMonthDay())
                .createDate(getCurrentDate())
                .modifyDate(getCurrentDate())
                .build();

        Long id = reservationDao.insertReservation(result);
        param.getPrices()
                        .forEach(p -> reservationDao.insertReservationPrice(p, id));

        result.setReservationInfoId(id.intValue());


        return new ReservationResponse(result);
    }

    private int getTotalPrice(ReservationParam param) {

        return param.getPrices().stream()
                .mapToInt(p -> productDao.getPrice(p.getProductPriceId()) * p.getCount())
                .sum();
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

    @Override
    public void addCommentImageFile(int reservationInfoId, int commentId, MultipartFile file) {
        String filename = file.getOriginalFilename();

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_SAVE_ROUTE + filename);
                InputStream inputStream = file.getInputStream()
        ) {
            int readCount = 0;
            byte[] buffer = new byte[1024];

            while((readCount = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0, readCount);
            }
        } catch (Exception e) {
            log.error("IOException log = {}", e.toString());
        }

        long fileInfoId = reservationDao.insertFileInfo(file, filename, FILE_SAVE_ROUTE_FOLDER + filename);
        reservationDao.insertReservationUserCommentImage(reservationInfoId, fileInfoId, commentId);
    }
}
