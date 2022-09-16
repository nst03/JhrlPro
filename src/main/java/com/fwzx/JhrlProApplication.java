package com.fwzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@ServletComponentScan("com.fwzx.config")
@SpringBootApplication
@EnableCaching
public class JhrlProApplication {

	public static void main(String[] args) {

		SpringApplication.run(JhrlProApplication.class, args);
	}

}
