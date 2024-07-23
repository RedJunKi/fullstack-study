package com.example.fullstack_study_boostcourse;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
public class ViewController {

    @GetMapping("/aboutme")
    public String aboutMe() {
        return "aboutme.html";
    }

    @GetMapping("/my-photo")
    public String myPhoto() {
        return "photo.html";
    }

    @GetMapping("/aboutme/today")
    @ResponseBody
    public String today() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd h:m");
        return "현재시간 : " + LocalDateTime.now().format(dateTimeFormatter);
    }
}
