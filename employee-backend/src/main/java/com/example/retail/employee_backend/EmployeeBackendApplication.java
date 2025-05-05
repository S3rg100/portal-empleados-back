package com.example.retail.employee_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class EmployeeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeBackendApplication.class, args);
	}

}
