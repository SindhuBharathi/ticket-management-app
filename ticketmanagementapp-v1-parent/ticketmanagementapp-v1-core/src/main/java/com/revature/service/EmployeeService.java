package com.revature.service;

import java.util.List;

import com.revature.dao.FunctionsDAO;
import com.revature.exception.ServiceException;
import com.revature.model.Ticket;
import com.revature.validator.EmployeeValidator;

public class EmployeeService {
	
	EmployeeValidator employeeValidator=new EmployeeValidator();
	EmployeeLoginService loginService = new EmployeeLoginService();
	EmployeeTicketService employeeTicketService = new EmployeeTicketService();

	public String reply(String emailId, String password, int ticketId, String solution) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.reply(functionsDAO.getEmployeeId(emailId, password), ticketId, solution);
			return "Ticket replied";
		}
		else
			return null;
	}

	public String assign(String emailId, String password, int ticketId) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.assign(functionsDAO.getEmployeeId(emailId, password),ticketId);
			return "Ticket is assigned.";
		}
		else
			return null;
	}

	public String reAssign(String emailId, String password, int ticketId, int employeeId) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.reAssign(functionsDAO.getEmployeeId(emailId, password),ticketId, employeeId);
			return "Ticket is reassigned";
		}
		else
			return null;
	}
	
	public String delete(String emailId, String password, int ticketId) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			employeeTicketService.delete(functionsDAO.getEmployeeId(emailId, password),ticketId);
			return "Ticket is deleted";
		}
		else
			return null;
	}

	public List<Ticket> view(String emailId, String password) throws ServiceException {
		if (loginService.login(emailId, password)) {
			FunctionsDAO functionsDAO = new FunctionsDAO();
			return employeeTicketService.view(functionsDAO.getEmployeeId(emailId, password));
		}
		else
			return null;
	}
}
