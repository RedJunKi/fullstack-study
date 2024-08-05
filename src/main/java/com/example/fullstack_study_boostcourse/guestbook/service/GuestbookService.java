package com.example.fullstack_study_boostcourse.guestbook.service;

import com.example.fullstack_study_boostcourse.guestbook.dto.Guestbook;

import java.util.List;

public interface GuestbookService {
    Integer LIMIT = 5;

    List<Guestbook> getGuestBooks(Integer start);

    int deleteGuestBook(Long id, String ip);

    Guestbook addGuestBook(Guestbook guestbook, String ip);

    int getCount();
}
