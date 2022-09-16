package com.fwzx.util;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CodeUtils {
	private String[] patch = {"000000", "00000", "0000", "000", "00", "0", ""};

	public String generator(String tele) {
		int hash = tele.hashCode();
		int encryption = 20206666;
		long result = hash ^ encryption;
		long nowTime = System.currentTimeMillis();
		result = result ^ nowTime;
		long code = result % 1000000;
		code = code < 0 ? -code : code;
		String codeStr = code + "";
		int len = codeStr.length();
		return patch[len] + codeStr;
	}

	//如果把该函数写在SMSCodeServiceImpl中，会导致get调用的是成员函数，不能从缓存中读取数据
	@Cacheable(value = "smsCode", key = "#tele")
	public String get(String tele) {
		return null;
	}
}
