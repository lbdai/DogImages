package com.java.assessment.love.dogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value = {
		@PropertySource("classpath:./application.properties")})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
