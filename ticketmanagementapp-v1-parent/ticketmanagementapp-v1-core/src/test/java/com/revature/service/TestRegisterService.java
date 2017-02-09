package com.revature.service;

import com.revature.exception.ServiceException;

public class TestRegisterService {

	public static void main(String[] args) throws ServiceException {
		RegisterService registerService = new RegisterService();
		registerService.registerUser("new", "aaa", "aaa");
	}

}
