package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //啟動必寫的annotation
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);  
	}

}