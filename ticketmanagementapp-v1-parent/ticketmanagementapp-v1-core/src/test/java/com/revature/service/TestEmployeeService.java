package com.revature.service;

import com.revature.exception.ServiceException;

public class TestEmployeeService {

	public static void main(String[] args) throws ServiceException {
		EmployeeService employeeService=new EmployeeService();
//		employeeService.reAssign("aaaab@gmail.com", "aaa", 3,3);
//		employeeService.assign("bbb@gmail.com", "aaa", 4);
//		employeeService.reply("aaab@gmail.com", "aaa",4, "solution");
//		employeeService.view("aaab@gmail.com","aaa");
		employeeService.delete("bbb@gmail.com", "aaa", 4);
	}

}
