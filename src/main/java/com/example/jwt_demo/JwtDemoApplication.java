package com.example.jwt_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.jwt_demo.mapper")
public class JwtDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}

}
