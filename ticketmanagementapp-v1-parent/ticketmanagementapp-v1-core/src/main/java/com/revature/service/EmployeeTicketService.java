package com.revature.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.TicketDAO;
import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidatorException;
import com.revature.model.Ticket;
import com.revature.validator.EmployeeValidator;

public class EmployeeTicketService {
	EmployeeValidator employeeValidator = new EmployeeValidator();
	TicketDAO ticketDAO = new TicketDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();

	public void assign(int employeeId,int ticketId) throws ServiceException {
		try {
			Logger logger = Logger.getLogger(EmployeeTicketService.class.getName());
			employeeValidator.validateAssignEmployee(employeeId,ticketId);
			employeeDAO.assignEmployee(ticketId);
			logger.log(Level.INFO, "Ticket Assigned");
		} catch (ValidatorException e) {
			throw new ServiceException(" ", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Wrong", e);
		}
	}
	
	public void reAssign(int callingEmployeeId, int ticketId, int employeeId) throws ServiceException {
		try {
			Logger logger = Logger.getLogger(EmployeeTicketService.class.getName());
			employeeValidator.validateAssignEmployee(callingEmployeeId, ticketId, employeeId);
			employeeDAO.assignEmployee(ticketId, employeeId);
			logger.log(Level.INFO, "Ticket reassigned");
		} catch (ValidatorException e) {
			throw new ServiceException(" ", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Wrong", e);
		}
	}

	public void reply(int callingEmployeeId, int ticketId, String solution) throws ServiceException {
		try {
			Logger logger = Logger.getLogger(TicketService.class.getName());
			employeeValidator.validateEmployeeReply(callingEmployeeId, ticketId, solution);
			employeeDAO.employeeReply(ticketId, solution);
			logger.log(Level.INFO, "Ticket replied... ");
		} catch (ValidatorException e) {
			throw new ServiceException("  ", e);
		} catch (PersistenceException e) {
			throw new ServiceException("PE  ", e);
		}
	}
	
	public void view(int employeeId) throws ServiceException {
		try {
			Logger logger = Logger.getLogger(TicketService.class.getName());
			List<Ticket> list = ticketDAO.listByEmployeeId(employeeId);
			for (Ticket tck : list) {
				logger.info(tck.getId() + "\t" + tck.getUserId().getId() + "\t" + tck.getSubject() + "\t" + tck.getDescription() + "\t"
						+ tck.getDepartmentId().getId() + "\t" + tck.getPriorityId().getId() + "\t"
						+ tck.getCreatedDateTime() + "\t" + tck.getResolvedDateTime() + "\t" + tck.getStatus());
			}
		} catch (PersistenceException e) {
			throw new ServiceException("Employee not exists any ticket", e);
		}

	}
	
	public void delete(int employeeId, int ticketId) throws ServiceException {
		try {
			Logger logger = Logger.getLogger(EmployeeTicketService.class.getName());
			employeeValidator.validateDeleteTicket(employeeId, ticketId);
			ticketDAO.updateIsActive(ticketId);
			logger.log(Level.INFO, "Ticket deleted");
		} catch (ValidatorException e) {
			throw new ServiceException(" ", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Wrong", e);
		}
	}

}
