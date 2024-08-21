package com.example.fullstack_study_boostcourse.promotion.service;

import com.example.fullstack_study_boostcourse.promotion.domain.Promotion;
import com.example.fullstack_study_boostcourse.promotion.repository.PromotionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionDao promotionDao;

    @Override
    public List<Promotion> getAllPromotion() {
        return promotionDao.selectAll();
    }
}
