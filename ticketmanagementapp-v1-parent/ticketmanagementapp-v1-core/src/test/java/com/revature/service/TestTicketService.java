package com.revature.service;

import com.revature.exception.ServiceException;

public class TestTicketService {

	public static void main(String[] args) throws ServiceException {
		UserTicketService ticketService = new UserTicketService();
//		ticketService.create(1, "test final", "test to service");
//		ticketService.update(4, 1, "new update", "description");
//		ticketService.close(3, 1);
		ticketService.view(1);
	}

}
