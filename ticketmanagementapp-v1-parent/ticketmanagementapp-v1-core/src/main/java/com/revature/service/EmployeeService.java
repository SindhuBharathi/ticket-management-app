package com.revature.service;

import com.revature.dao.FunctionsDAO;
import com.revature.exception.ServiceException;
import com.revature.validator.EmployeeValidator;

public class EmployeeService {
	
	EmployeeValidator employeeValidator=new EmployeeValidator();
	EmployeeLoginService loginService = new EmployeeLoginService();
	EmployeeTicketService employeeTicketService = new EmployeeTicketService();

	public void reply(String emailId, String password, int ticketId, String solution) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.reply(functionsDAO.getEmployeeId(emailId, password), ticketId, solution);
		}
	}

	public void assign(String emailId, String password, int ticketId) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.assign(functionsDAO.getEmployeeId(emailId, password),ticketId);
		}
	}

	public void reAssign(String emailId, String password, int ticketId, int employeeId) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.reAssign(functionsDAO.getEmployeeId(emailId, password),ticketId, employeeId);
		}
	}
	
	public void delete(String emailId, String password, int ticketId) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.delete(functionsDAO.getEmployeeId(emailId, password),ticketId);
		}
	}

	public void view(String emailId, String password) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.view(functionsDAO.getEmployeeId(emailId, password));
		}
	}
}
