package com.example.fullstack_study_boostcourse.springjdbc_study.test;

import com.example.fullstack_study_boostcourse.springjdbc_study.AppConfig;
import com.example.fullstack_study_boostcourse.springjdbc_study.Role;
import com.example.fullstack_study_boostcourse.springjdbc_study.RoleDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class JDBCTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        RoleDao roleDao = ac.getBean(RoleDao.class);


        Role select = roleDao.select(500);
        System.out.println(select);

        int delete = roleDao.delete(500);
        System.out.println(delete);

        select = roleDao.select(500);
        System.out.println(select);

    }
}
