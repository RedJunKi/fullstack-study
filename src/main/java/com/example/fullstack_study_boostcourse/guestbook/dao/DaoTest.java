package com.example.fullstack_study_boostcourse.guestbook.dao;

import com.example.fullstack_study_boostcourse.guestbook.config.AppConfig;
import com.example.fullstack_study_boostcourse.guestbook.dto.Guestbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class DaoTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        GuestbookDao guestbookDao = ac.getBean(GuestbookDao.class);

        Guestbook guestbook = new Guestbook();
        guestbook.setName("korean");
        guestbook.setContent("한글");
        guestbook.setRegdate(new Date());

        Long id = guestbookDao.insert(guestbook);
        System.out.println("id = " + id);


    }
}
