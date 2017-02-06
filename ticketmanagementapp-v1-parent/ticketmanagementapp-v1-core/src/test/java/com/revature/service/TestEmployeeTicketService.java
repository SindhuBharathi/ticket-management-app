package com.revature.service;

import com.revature.exception.ServiceException;

public class TestEmployeeTicketService {

	public static void main(String[] args) throws ServiceException {

		EmployeeTicketService employeeTicketService = new EmployeeTicketService();
//		employeeTicketService.reAssign(1, 5);
//		employeeTicketService.reply(3, "solution");
//		employeeTicketService.assign(4);
		employeeTicketService.view(5);
		
	}

}
