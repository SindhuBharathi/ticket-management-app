package com.revature.service;

import com.revature.exception.ServiceException;

public class TestLoginService {

	public static void main(String[] args) throws ServiceException {
/*		UserLoginService loginService = new UserLoginService();
		System.out.println(loginService.login("abaa@gmail.com", "aaapwd"));
*/	
		EmployeeLoginService loginService = new EmployeeLoginService();
		System.out.println(loginService.login("aaa@gmail.com", "aaapwd"));
		
	}

}
