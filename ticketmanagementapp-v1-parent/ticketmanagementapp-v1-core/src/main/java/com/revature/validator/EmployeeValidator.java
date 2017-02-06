package com.revature.validator;

import com.revature.dao.FunctionsDAO;
import com.revature.exception.ValidatorException;
import com.revature.util.ValidatorUtil;

public class EmployeeValidator {
	ValidatorUtil validatorUtil = new ValidatorUtil();
	FunctionsDAO functionsDAO = new FunctionsDAO();

	public void validateAssignEmployee(int employeeId,int ticketId) throws ValidatorException/*, PersistenceException */{
		validatorUtil.rejectIfNegativeOrZeroOrNull(ticketId, "TicketId");
		String status = functionsDAO.getStatus(ticketId);
		validatorUtil.rejectIfStatusClosed(status, "Cannot assign ");
		validatorUtil.rejectIfStatusNotOpen(status, "Already Employee is assigned");
		int roleId=functionsDAO.getEmployeeRoleId(employeeId);
		validatorUtil.rejectIfNotEqual(roleId,1, "Not admin");
		int departmentId=functionsDAO.getEmployeeDepartmentId(employeeId);
		int departmentIdfromTicketId=functionsDAO.getDepartmentIdFomTicketId(ticketId);
		validatorUtil.rejectIfNotEqual(departmentId, departmentIdfromTicketId, "Not belong to same dept");
/*
		Logger logger = Logger.getLogger(TicketValidator.class.getName());
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.assignEmployee(ticketId);
		logger.log(Level.INFO, "Ticket assigned...");

*/	}
	
	public void validateAssignEmployee(int callingEmployeeId, int ticketId, int employeeId) throws ValidatorException/*, PersistenceException */{
		validatorUtil.rejectIfNegativeOrZeroOrNull(ticketId, "TicketId");
		String status = functionsDAO.getStatus(ticketId);
		validatorUtil.rejectIfStatusClosed(status, "Cannot assign ");
		int employeeIdFromTicket = functionsDAO.getEmployeeIdFromTicketId(ticketId);
		validatorUtil.rejectIfNotEqual(callingEmployeeId, employeeIdFromTicket, "Ticket is not assigned to this employee");
		validatorUtil.rejectIfEqual(employeeId, employeeIdFromTicket, " ");
		int callingEmployeeDepartmentId=functionsDAO.getEmployeeDepartmentId(callingEmployeeId);
		int employeeDepartmentId=functionsDAO.getEmployeeDepartmentId(employeeId);
		validatorUtil.rejectIfNotEqual(callingEmployeeDepartmentId, employeeDepartmentId, "not same dept");
/*
		Logger logger = Logger.getLogger(TicketValidator.class.getName());
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.assignEmployee(ticketId);
		logger.log(Level.INFO, "Ticket assigned...");

*/	}
	
	public void validateEmployeeReply(int callingEmployeeId, int ticketId,String solution) throws ValidatorException/*, PersistenceException*/
	{
		validatorUtil.rejectIfNegativeOrZeroOrNull(ticketId, "TicketId");
		validatorUtil.rejectIfNullOrEmpty(solution, "Solution");
		String status = functionsDAO.getStatus(ticketId);
		validatorUtil.rejectIfStatusClosed(status, "Cannot reply ");
		validatorUtil.rejectIfStatusOpen(status, "Employee not assigned");
		validatorUtil.rejectIfNotEqual(callingEmployeeId, functionsDAO.getEmployeeIdFromTicketId(ticketId), "Ticket is not assigned to this employee");
/*		
		Logger logger = Logger.getLogger(TicketValidator.class.getName());
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.employeeReply(ticketId, solution);
		logger.log(Level.INFO, "Ticket resolved...");
		
*/	}
	
	public void validateDeleteTicket(int employeeId,int ticketId) throws ValidatorException/*, PersistenceException */{
		validatorUtil.rejectIfNegativeOrZeroOrNull(ticketId, "TicketId");
		int roleId=functionsDAO.getEmployeeRoleId(employeeId);
		validatorUtil.rejectIfNotEqual(roleId,1, "Not admin");
		int departmentId=functionsDAO.getEmployeeDepartmentId(employeeId);
		int departmentIdfromTicketId=functionsDAO.getDepartmentIdFomTicketId(ticketId);
		validatorUtil.rejectIfNotEqual(departmentId, departmentIdfromTicketId, "Not belong to same dept");
	}
}
