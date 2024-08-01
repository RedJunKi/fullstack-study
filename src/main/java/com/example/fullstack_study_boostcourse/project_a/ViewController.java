package com.example.fullstack_study_boostcourse.project_a;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
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
    public void today(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>" +
                "<meta charset=\"utf-8\">" +
                "<title>현재시간</title>" +
                "<style>" +
                "body { text-align: center; }" +
                "h1 { font-size: 3em; }" +
                "</style>" +
                "</head>");
        writer.println("<body>");
        writer.println("<a href=\"/\">메인메뉴</a>");


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd hh:mm");
        String today = LocalDateTime.now().format(dateTimeFormatter);
        writer.println("<h1>현재 시간 : " + today + "</h1>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }
}
