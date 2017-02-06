package com.revature.service;

import com.revature.dao.FunctionsDAO;
import com.revature.exception.ServiceException;

public class UserService {
	UserLoginService loginService = new UserLoginService();
	TicketService ticketService = new TicketService();

	public void create(String emailId, String password, String subject, String description) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			ticketService.create(functionsDAO.getUserId(emailId, password), subject, description);
		}
	}

	public void update(String emailId, String password, int ticketId, String description)
			throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			ticketService.update(ticketId, functionsDAO.getUserId(emailId, password), description);
		}
	}

	public void close(String emailId, String password, int ticketId) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			ticketService.close(ticketId, functionsDAO.getUserId(emailId, password));
		}
	}

	public void view(String emailId, String password) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			ticketService.view(functionsDAO.getUserId(emailId, password));
		}
	}
}
