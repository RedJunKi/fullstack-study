package com.example.fullstack_study_boostcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FullstackStudyBoostcourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullstackStudyBoostcourseApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<RolesServlet> roleServletRegistration() {
		return new ServletRegistrationBean<>(new RolesServlet(), "/roles");
	}

	@Bean
	public ServletRegistrationBean<RoleByIdServlet> roleByIdServletServletRegistrationBean() {
		return new ServletRegistrationBean<>(new RoleByIdServlet(), "/roles/*");
	}
}
