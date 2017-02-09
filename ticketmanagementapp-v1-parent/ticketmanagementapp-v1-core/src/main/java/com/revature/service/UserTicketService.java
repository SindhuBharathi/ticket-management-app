package com.revature.service;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.revature.dao.TicketDAO;
import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidatorException;
import com.revature.model.Department;
import com.revature.model.Priority;
import com.revature.model.Ticket;
import com.revature.model.TicketList;
import com.revature.model.User;
import com.revature.validator.TicketValidator;

public class UserTicketService {
	TicketValidator ticketValidator = new TicketValidator();
	TicketDAO ticketDAO = new TicketDAO();
	UserLoginService loginService = new UserLoginService();

	public String create(int userId, String subject, String description, int departmentId, int priorityId) throws ServiceException {
		try {
//			Logger logger = Logger.getLogger(TicketService.class.getName());
			ticketValidator.validateCreate(userId, subject, description);
			Ticket t = new Ticket();
			t.setSubject(subject);
			t.setDescription(description);
			User u = new User();
			u.setId(userId);
			t.setUserId(u);
			Department d=new Department();
			d.setId(departmentId);
			t.setDepartmentId(d);
			Priority p=new Priority();
			p.setId(priorityId);
			t.setPriorityId(p);
			int ticketId = ticketDAO.create(t);
//			logger.log(Level.INFO, "Ticket created... Ticket id " + ticketId);
			return "Ticket created... Ticket id is "+ticketId;
		} catch (ValidatorException e) {
			throw new ServiceException("Ticket not created", e);
		} catch (PersistenceException e) {
			throw new ServiceException("PE ", e);
		} catch (EmailException e) {
			throw new ServiceException("PE ", e);
		}

	}

	public String update(int ticketId, int userId, String description) throws ServiceException {
		try {
//			Logger logger = Logger.getLogger(TicketService.class.getName());
			ticketValidator.validateUpdate(ticketId, userId, description);
			Ticket t = new Ticket();
			t.setId(ticketId);
			t.setDescription(description);
			User u = new User();
			u.setId(userId);
			t.setUserId(u);
			ticketDAO.updateTicket(t);
//			logger.log(Level.INFO, "Ticket updated");
			return "Ticket updated successfully...";
		} catch (ValidatorException e) {
			throw new ServiceException("Ticket not updated", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Wrong", e);
		}
	}

	public String close(int ticketId, int userId) throws ServiceException {
		try {
//			Logger logger = Logger.getLogger(TicketService.class.getName());
			ticketValidator.validateClose(ticketId, userId);
			Ticket t = new Ticket();
			t.setId(ticketId);
			User u = new User();
			u.setId(userId);
			t.setUserId(u);
			ticketDAO.closeTicket(t);
//			logger.log(Level.INFO, "Ticket closed");
			return "Ticket closed.";
		} catch (ValidatorException e) {
			throw new ServiceException("Already closed", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Wrong", e);
		}
	}

/*	public void view(int userId) throws ServiceException {
		try {
			Logger logger = Logger.getLogger(TicketService.class.getName());
			List<TicketList> list = ticketDAO.listByUserId(userId);
			for (TicketList tck : list) {
				logger.info(tck.getId() + "\t" + tck.getSubject() + "\t" + tck.getDescription() + "\t"
						+ tck.getCreatedDateTime() + "\t" + tck.getStatus());
			}
		} catch (PersistenceException e) {
			throw new ServiceException("Not yet created any ticket", e);
		}

	}
*/
	public List<TicketList> view(int userId) throws ServiceException {
		try {
			return ticketDAO.listByUserId(userId);
		} catch (PersistenceException e) {
			throw new ServiceException("Not yet created any ticket", e);
		}

	}
	
}
