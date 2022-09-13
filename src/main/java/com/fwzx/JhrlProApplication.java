package com.fwzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com.fwzx.config")
@SpringBootApplication
public class JhrlProApplication {

	public static void main(String[] args) {

		SpringApplication.run(JhrlProApplication.class, args);
	}

}
