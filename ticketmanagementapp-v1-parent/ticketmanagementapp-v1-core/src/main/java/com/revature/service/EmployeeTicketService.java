package com.revature.service;

import java.util.List;
import org.apache.commons.mail.EmailException;

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
			employeeValidator.validateAssignEmployee(employeeId,ticketId);
			employeeDAO.assignEmployee(ticketId);
		} catch (ValidatorException e) {
			throw new ServiceException("Ticket not assigned", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Wrong", e);
		} catch (EmailException e) {
			throw new ServiceException("Mail not sent", e);
		}
	}
	
	public void reAssign(int callingEmployeeId, int ticketId, int employeeId) throws ServiceException {
		try {
			employeeValidator.validateAssignEmployee(callingEmployeeId, ticketId, employeeId);
			employeeDAO.assignEmployee(ticketId, employeeId);
		} catch (ValidatorException e) {
			throw new ServiceException("Ticket not assigned", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Wrong", e);
		}
	}

	public void reply(int callingEmployeeId, int ticketId, String solution) throws ServiceException {
		try {
			employeeValidator.validateEmployeeReply(callingEmployeeId, ticketId, solution);
			employeeDAO.employeeReply(ticketId, solution);
		} catch (ValidatorException e) {
			throw new ServiceException("Ticket not replied", e);
		} catch (PersistenceException e) {
			throw new ServiceException("PE  ", e);
		} catch (EmailException e) {
			throw new ServiceException("Mail not sent", e);
		}
	}
		
	public void delete(int employeeId, int ticketId) throws ServiceException {
		try {
			employeeValidator.validateDeleteTicket(employeeId, ticketId);
			ticketDAO.updateIsActive(ticketId);
		} catch (ValidatorException e) {
			throw new ServiceException("Ticket not deleted", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Wrong", e);
		}
	}
	
/*	public void view(int employeeId) throws ServiceException {
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
*/
	public List<Ticket> view(int employeeId) throws ServiceException {
		try {
			return ticketDAO.listByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			throw new ServiceException("Employee not exists any ticket", e);
		}
	}

}
