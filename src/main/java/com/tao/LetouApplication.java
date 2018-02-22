package com.tao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.tao.mapper")
public class LetouApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetouApplication.class, args);
	}
}
