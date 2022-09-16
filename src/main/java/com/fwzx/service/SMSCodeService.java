package com.fwzx.service;

import com.fwzx.pojo.SMSCode;

public interface SMSCodeService {
	public String sendCodeToSMS(String tele);

	public boolean checkCode(SMSCode smsCode);
}