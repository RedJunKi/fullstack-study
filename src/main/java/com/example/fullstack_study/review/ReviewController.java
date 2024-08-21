package com.example.fullstack_study.review;

import com.example.fullstack_study.reservation.domain.ReservationInfo;
import com.example.fullstack_study.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/reviewwrite")
@RequiredArgsConstructor
public class ReviewController {
    private final ReservationService reservationService;

    @GetMapping
    public String getPostReviewPage(@SessionAttribute("reservationEmail") String email, @RequestParam int reservationInfoId, Model model) {

        List<ReservationInfo> reservationByEmail = reservationService.getReservationByEmail(email);
        ReservationInfo reservationInfo = reservationByEmail.stream()
                .filter(ri -> ri.getReservationInfoId() == reservationInfoId)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("잘못된 요청입니다.")
                );

        model.addAttribute("reservationInfo", reservationInfo);
        return "reviewwrite";
    }
}
