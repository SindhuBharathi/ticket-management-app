package com.revature.service;

import com.revature.exception.ServiceException;

public class TestUserService {

	public static void main(String[] args) throws ServiceException {
		UserService userService = new UserService();
		userService.create("abaaa@gmail.com", "aaa", "hai", "hello");
//		 userService.update("abaaa@gmail.com", "aaapwd", 10,"abaaa@gmail.com", "aaapwd");
		// userService.close("abaaa@gmail.com", "aaapwd", 8);
		userService.view("abaaa@gmail.com", "aaa");
	}

}
