package com.wei.springbootmybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wei")
@MapperScan("com.wei.springbootmybatisdemo.mapper")
public class SpringbootmybatisdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmybatisdemoApplication.class, args);
	}


}
