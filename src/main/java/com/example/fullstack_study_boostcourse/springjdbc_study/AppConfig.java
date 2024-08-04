package com.example.fullstack_study_boostcourse.springjdbc_study;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.example.fullstack_study_boostcourse.springjdbc_study")
@Import(DbConfig.class)
public class AppConfig {
}
