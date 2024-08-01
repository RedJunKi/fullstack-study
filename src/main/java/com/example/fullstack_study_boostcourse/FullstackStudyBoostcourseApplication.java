package com.example.fullstack_study_boostcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

//@SpringBootApplication
public class FullstackStudyBoostcourseApplication {

	public static void main(String[] args) {
//		SpringApplication.run(FullstackStudyBoostcourseApplication.class, args);
		RoleDao roleDao = new RoleDao();


		List<Role> roles = roleDao.getRoles();
		roles
				.forEach(System.out::println);
	}
}
