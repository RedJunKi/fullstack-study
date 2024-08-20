package com.example.fullstack_study_boostcourse.login;

import com.example.fullstack_study_boostcourse.reservation.ReservationInfo;
import com.example.fullstack_study_boostcourse.reservation.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final ReservationService reservationService;
    private final int SESSION_MAINTAIN_TIME = 60 * 60 * 24;

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam("resrv_email") String email, HttpSession session, Model model, RedirectAttributes redirectAttributes) {

        List<ReservationInfo> reservationByEmail = reservationService.getReservationByEmail(email);

        if (reservationByEmail.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "예약 정보가 존재하지 않습니다.");
            return "redirect:login";
        }

        session.setAttribute("reservationEmail", email);
        session.setMaxInactiveInterval(SESSION_MAINTAIN_TIME);
        return "redirect:myreservation";
    }
}
