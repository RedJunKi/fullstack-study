package com.example.fullstack_study_boostcourse.guestbook.controller;

import com.example.fullstack_study_boostcourse.guestbook.dto.Guestbook;
import com.example.fullstack_study_boostcourse.guestbook.service.GuestbookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static com.example.fullstack_study_boostcourse.guestbook.service.GuestbookService.*;

//@Controller
public class GuestbookController {
    @Autowired
    private GuestbookService guestbookService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
                       Model model) {
        List<Guestbook> guestBooks = guestbookService.getGuestBooks(start);

        int count = guestbookService.getCount();
        int pageCount = count / LIMIT;
        if (count % LIMIT > 0) {
            pageCount++;
        }

        List<Integer> pageStartList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            pageStartList.add(i * LIMIT);
        }

        model.addAttribute("list", guestBooks);
        model.addAttribute("count", count);
        model.addAttribute("pageStartList", pageStartList);
        return "list";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute Guestbook guestbook,
                        HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        guestbookService.addGuestBook(guestbook, clientIp);
        return "redirect:list";
    }
}
