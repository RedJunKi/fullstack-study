package com.example.fullstack_study.product.service;

import com.example.fullstack_study.product.domain.DisplayInfoResponse;
import com.example.fullstack_study.product.domain.Product;
import com.example.fullstack_study.product.domain.ProductResponse;
import com.example.fullstack_study.product.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductResponse getAllProductsByCategory(Integer categoryId, Integer start) {
        ProductResponse result = new ProductResponse();

        List<Product> products = productDao.selectAllProductsByCategory(categoryId, start, LIMIT);
        result.setProducts(products);
        result.setTotalCount(getDisplayInfoCountByCategory(categoryId));

        return result;
    }

    @Override
    public ProductResponse getAllProducts(int start) {
        ProductResponse result = new ProductResponse();

        List<Product> products = productDao.selectAllProducts(start, LIMIT);
        result.setProducts(products);
        result.setTotalCount(getAllDisplayInfoCount());

        return result;
    }

    @Override
    public DisplayInfoResponse getDisplayInfoById(Integer displayInfoId) {
        return productDao.selectDisplayInfoById(displayInfoId);
    }

    @Override
    public int getAllDisplayInfoCount() {
        return productDao.getAllDisplayInfoCount();
    }

    @Override
    public int getDisplayInfoCountByCategory(int categoryId) {
        return productDao.getDisplayInfoCountByCategory(categoryId);
    }
}
