package com.example.fullstack_study_boostcourse.service;

import com.example.fullstack_study_boostcourse.guestbook.dao.LogDao;
import com.example.fullstack_study_boostcourse.guestbook.dto.Guestbook;
import com.example.fullstack_study_boostcourse.guestbook.service.GuestbookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@Transactional
class GuestbookServiceImplTest {
    @Autowired
    private LogDao logDao;

    @Autowired
    private GuestbookService guestbookService;

    @Test
    void 서비스테스트() {
        //given
        Guestbook guestbook = new Guestbook();
        guestbook.setName("hong");
        guestbook.setContent("hi");
        guestbook.setRegdate(new Date());
        //when
        Guestbook result = guestbookService.addGuestBook(guestbook, "127.0.0.1");

        //then
        Assertions.assertThat(result.getName()).isEqualTo(guestbook.getName());
    }
}