package com.example.fullstack_study_boostcourse.springjdbc_study.test;

import com.example.fullstack_study_boostcourse.springjdbc_study.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        DataSource ds = ac.getBean(DataSource.class);
        Connection conn = null;

        try {
            conn = ds.getConnection();
            if (conn != null) {
                System.out.println("접속 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
