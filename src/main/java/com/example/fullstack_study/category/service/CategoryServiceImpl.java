package com.example.fullstack_study.category.service;

import com.example.fullstack_study.category.domain.Category;
import com.example.fullstack_study.category.domain.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        return categoryDao.selectAll();
    }
}
