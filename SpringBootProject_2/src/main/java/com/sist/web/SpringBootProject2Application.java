package com.sist.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.sist.web.dao")
@ComponentScan(basePackages = {"com.sist.web.service","com.sist.web.controller","com.sist.web.dao"})
public class SpringBootProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProject2Application.class, args);
	}

}
