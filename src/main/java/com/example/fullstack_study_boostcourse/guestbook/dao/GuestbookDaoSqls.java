package com.example.fullstack_study_boostcourse.guestbook.dao;

public class GuestbookDaoSqls {
    public static final String SELECT_PAGING = "SELECT id, name, content, regdate FROM guestbook ORDER BY id desc limit :start, :limit";
    public static final String DELETE_BY_ID = "DELETE FROM guestbook where id = :id";
    public static final String SELECT_COUNT = "SELECT COUNT(*) FROM guestbook";
}
