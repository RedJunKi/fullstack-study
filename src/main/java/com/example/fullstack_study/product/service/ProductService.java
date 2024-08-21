package com.example.fullstack_study.product.service;

import com.example.fullstack_study.product.domain.DisplayInfoResponse;
import com.example.fullstack_study.product.domain.ProductResponse;

public interface ProductService {
    Integer LIMIT = 4;

    ProductResponse getAllProductsByCategory(Integer categoryId, Integer start);

    DisplayInfoResponse getDisplayInfoById(Integer id);

    int getAllDisplayInfoCount();

    int getDisplayInfoCountByCategory(int categoryId);

    ProductResponse getAllProducts(int start);
}
