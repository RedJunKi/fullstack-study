package com.example.fullstack_study.product.repository;

public class ProductDaoSqls {
    public static final String SELECT_PAGING_WITH_CATEGORY = "SELECT id, name, content, regdate FROM guestbook ORDER BY id desc limit :start, :limit";
    public static final String SELECT_BY_ID = "SELECT FROM product where id = :id";
    public static final String SELECT_COUNT = "SELECT COUNT(*) FROM product";
}
