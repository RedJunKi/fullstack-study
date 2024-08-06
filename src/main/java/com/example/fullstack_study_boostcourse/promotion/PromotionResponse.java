package com.example.fullstack_study_boostcourse.promotion;

import java.util.List;

public class PromotionResponse {
    private List<Promotion> items;

    public PromotionResponse(List<Promotion> items) {
        this.items = items;
    }

    public List<Promotion> getItems() {
        return items;
    }

    public void setItems(List<Promotion> items) {
        this.items = items;
    }
}