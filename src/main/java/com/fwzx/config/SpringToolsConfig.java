package com.fwzx.config;

import com.fwzx.util.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author 牛松涛
 * @version 1.0
 * @Description:
 * @Date 2017/11/25 10:01
 */
@Configuration
public class SpringToolsConfig {

	@Bean
	public SpringContextHolder springContextHolderConfig() throws IOException {
		SpringContextHolder factory = new SpringContextHolder();
		return factory;
	}



}


