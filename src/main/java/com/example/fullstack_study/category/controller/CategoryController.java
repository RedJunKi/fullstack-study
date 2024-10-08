package com.example.fullstack_study.category.controller;

import com.example.fullstack_study.category.service.CategoryService;
import com.example.fullstack_study.category.domain.Category;
import com.example.fullstack_study.category.domain.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponse> getCategories() {
        List<Category> categories = categoryService.getCategories();
        CategoryResponse result = new CategoryResponse(categories);
        return ResponseEntity.ok(result);
    }
}
