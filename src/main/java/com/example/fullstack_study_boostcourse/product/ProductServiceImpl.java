package com.example.fullstack_study_boostcourse.product;

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
    public List<Product> getAllProductsByCategory(Integer categoryId, Integer start) {

        return productDao.selectAllProductsByCategory(categoryId, start, LIMIT);

    }

    @Override
    public DisplayInfoResponse getDisplayInfoById(Integer id) {
        return productDao.selectDisplayInfoById(id);
    }

    @Override
    public int getCount() {
        return productDao.getCount();
    }

    @Override
    public List<Product> getAllProducts(int start) {
        return productDao.selectAllProducts(start, LIMIT);
    }
}
