package com.example.fullstack_study.myreservation;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/myreservation")
public class MyReservationController {

    @GetMapping
    public String getMyReservation(@SessionAttribute("reservationEmail") String email, Model model, HttpSession session) {
        model.addAttribute("reservationEmail", email);
        return "myreservation";
    }
}
