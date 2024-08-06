package com.example.fullstack_study_boostcourse.product;

import java.util.List;

public interface ProductService {
    Integer LIMIT = 5;

    List<Product> getAllProductsByCategory(Integer categoryId, Integer start);

    DisplayInfoResponse getDisplayInfoById(Integer id);

    int getCount();

    List<Product> getAllProducts(int start);
}
