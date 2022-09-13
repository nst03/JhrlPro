package com.fwzx.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/init")
public class InitController implements ApplicationListener<ContextRefreshedEvent> {


	/**
	 * 初始化代码放在这里
	 *
	 * @param contextRefreshedEvent
	 */
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		// TODO Auto-generated method stub
		System.out.println("--------------------------------kaiqi");
	}
}
