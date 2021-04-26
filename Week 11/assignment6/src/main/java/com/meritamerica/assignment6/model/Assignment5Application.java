package com.meritamerica.assignment6.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.meritamerica.assignment6.model.repository")
@SpringBootApplication
public class Assignment5Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment5Application.class, args);
		MeritBank.readFromFile("src/test/testMeritBank_good.txt");

	}
}
