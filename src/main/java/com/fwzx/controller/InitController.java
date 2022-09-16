package com.fwzx.controller;

import com.fwzx.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

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
//		test();
	}

	@Autowired
	private TestServiceImpl testService;

	public void test() {
		String token = UUID.randomUUID().toString();
//		System.out.println(testService.getToken(token));
		testService.setToken(token, "测试数据");
		System.out.println(testService.getToken(token));
		testService.delToken(token);
		System.out.println(testService.getToken(token));
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(testService.getToken(token));
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(testService.getToken(token));
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(testService.getToken(token));
//		testService.setToken(token, "测试数据222");
//		System.out.println(testService.getToken(token));
	}
}
