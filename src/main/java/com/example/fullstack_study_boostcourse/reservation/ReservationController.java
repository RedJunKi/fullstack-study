package com.example.fullstack_study_boostcourse.reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Slf4j
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<ReservationInfoResponse> getReservationByEmail(@RequestParam(value = "reservationEmail") String email) {
        List<ReservationInfo> reservationByEmail = reservationService.getReservationByEmail(email);
        ReservationInfoResponse result = new ReservationInfoResponse(reservationByEmail);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationParam param) {
        ReservationResponse result = reservationService.addReservation(param);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{reservationInfoId}/comments")
    public ResponseEntity<CommentResponse> addCommentToReservation(@PathVariable("reservationInfoId") int reservationInfoId,
                                                                   @RequestBody CommentParamDto commentParamDto) {

        CommentResponse commentResponse = reservationService
                .addCommentToReservation(reservationInfoId, commentParamDto.getProductId(), commentParamDto.getComment(), commentParamDto.getScore());

        int commentId = commentResponse.getCommentId();

        return ResponseEntity.ok(commentResponse);
    }

    @PostMapping("/{reservationInfoId}/image")
    public void addImageFile(@PathVariable int reservationInfoId, @RequestParam("commentId") int commentId, @RequestParam(value = "imageFile") MultipartFile file) {

        reservationService.addCommentImageFile(reservationInfoId, commentId ,file);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> deleteReservation(@PathVariable("reservationId") int id) {
        ReservationResponse result = reservationService.cancelReservation(id);
        return ResponseEntity.ok(result);
    }
}
