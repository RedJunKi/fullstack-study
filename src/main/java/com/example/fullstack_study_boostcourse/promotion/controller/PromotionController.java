package com.example.fullstack_study_boostcourse.promotion.controller;

import com.example.fullstack_study_boostcourse.promotion.domain.Promotion;
import com.example.fullstack_study_boostcourse.promotion.domain.PromotionResponse;
import com.example.fullstack_study_boostcourse.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promotions")
    public ResponseEntity<PromotionResponse> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotion();
        PromotionResponse result = new PromotionResponse(promotions);
        return ResponseEntity.ok(result);
    }
}
