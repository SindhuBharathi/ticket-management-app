package com.revature.service;

import java.util.List;

import com.revature.dao.FunctionsDAO;
import com.revature.exception.ServiceException;
import com.revature.model.TicketList;

public class UserService {
	UserLoginService loginService = new UserLoginService();
	TicketService ticketService = new TicketService();

	public String create(String emailId, String password, String subject, String description, String department, String priority) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			return ticketService.create(functionsDAO.getUserId(emailId, password), subject, description, functionsDAO.getDepartmentId(department), functionsDAO.getPriorityId(priority));
		}
		else
			return null;
	}

	public String update(String emailId, String password, int ticketId, String description)
			throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			return ticketService.update(ticketId, functionsDAO.getUserId(emailId, password), description);
		}
		else 
			return null;
	}

	public String close(String emailId, String password, int ticketId) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			return ticketService.close(ticketId, functionsDAO.getUserId(emailId, password));
		}
		else
			return null;
	}

/*	public void view(String emailId, String password) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			ticketService.view(functionsDAO.getUserId(emailId, password));
		}
	}
*/
	public List<TicketList> view(String emailId, String password) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			return ticketService.view(functionsDAO.getUserId(emailId, password));	
		}
		else
			return null;
	}
}
