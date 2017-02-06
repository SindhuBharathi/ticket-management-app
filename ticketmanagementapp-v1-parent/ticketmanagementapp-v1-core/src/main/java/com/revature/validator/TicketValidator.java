package com.revature.validator;

import com.revature.dao.FunctionsDAO;
import com.revature.exception.ValidatorException;
import com.revature.util.ValidatorUtil;

public class TicketValidator {
	ValidatorUtil validatorUtil = new ValidatorUtil();
	FunctionsDAO functionsDAO = new FunctionsDAO();

	public void validateCreate(int userId, String subject, String description)
			throws ValidatorException /* , PersistenceException */ {
		validatorUtil.rejectIfNegativeOrZeroOrNull(userId, "UserId");
		validatorUtil.rejectIfNullOrEmpty(subject, "Subject");
		validatorUtil.rejectIfNullOrEmpty(description, "Description");

		/*
		 * Logger logger = Logger.getLogger(TicketValidator.class.getName());
		 * TicketDAO ticketDAO=new TicketDAO(); int
		 * ticketId=ticketDAO.create(userId,subject,description);
		 * logger.log(Level.INFO, "Ticket created... Ticket id "+ticketId);
		 */
	}

	public void validateUpdate(int ticketId, int userIdFromLogin, String description)
			throws ValidatorException /*, PersistenceException */{
		validatorUtil.rejectIfNegativeOrZeroOrNull(ticketId, "TicketId");
		validatorUtil.rejectIfNullOrEmpty(description, "Description");

		int userIdFromTicket = functionsDAO.getUserIdFromTicketId(ticketId);
		validatorUtil.rejectIfNotEqual(userIdFromLogin, userIdFromTicket, "Ticket is not created by this user");
		String status=functionsDAO.getStatus(ticketId);
		validatorUtil.rejectIfStatusClosed(status, "Cannot update a ticket ");	
		
	/*	  Logger logger = Logger.getLogger(TicketValidator.class.getName());
		  TicketDAO ticketDAO=new TicketDAO(); 
		  Ticket t=new Ticket();
		  t.setId(ticketId);
		  t.setSubject(subject);
		  t.setDescription(description);
		  User u=new User();
		  u.setId(userIdFromTicket);
		  t.setUserId(u);
		  ticketDAO.updateTicket(t);
		  logger.log(Level.INFO, "Ticket updated... ");
		 */
	}
	public void validateClose(int ticketId, int userIdFromLogin) throws ValidatorException /*, PersistenceException*/
	{
		String status=functionsDAO.getStatus(ticketId);
		validatorUtil.rejectIfStatusClosed(status, "Cannot close a ticket ");
		int userIdFromTicket = functionsDAO.getUserIdFromTicketId(ticketId);
		validatorUtil.rejectIfNotEqual(userIdFromLogin, userIdFromTicket, "Ticket is not created by this user");
		  		
/*		Logger logger = Logger.getLogger(TicketValidator.class.getName());
		  TicketDAO ticketDAO=new TicketDAO(); 
		  Ticket t=new Ticket();
		  t.setId(ticketId);
		  User u=new User();
		  u.setId(userIdFromTicket);
		  t.setUserId(u);
		  ticketDAO.closeTicket(t);
		  logger.log(Level.INFO, "Ticket closed... ");
*/	
		
	}
	
}
