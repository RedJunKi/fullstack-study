package com.example.fullstack_study_boostcourse.booking.controller;

import com.example.fullstack_study_boostcourse.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public String showBookingPage(@RequestParam int id, Model model) {
        String reservationDate = bookingService.getRandomReservationDate();
        model.addAttribute("reservationDateTime", reservationDate);

        return "reserve";
    }
}
