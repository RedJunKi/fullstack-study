package com.example.fullstack_study.booking.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BookingService {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yy.MM.dd");
    private final int DATE_BOUNDARY = 5;

    public String getRandomReservationDate() {
        LocalDate now = LocalDate.now();
        int randomValue = (int) (Math.random() * 6);
        LocalDate randomDate = now.plusDays(randomValue);
        return randomDate.format(DATE_FORMAT);
    }
}
