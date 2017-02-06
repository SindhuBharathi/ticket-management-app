package com.revature.validator;

import com.revature.exception.PersistenceException;
import com.revature.exception.ValidatorException;

public class TestTicketValidator {
	public static void main(String[] args) throws ValidatorException, PersistenceException {
		TicketValidator ticketValidator = new TicketValidator();
//		ticketValidator.validateCreate("new test", "to test validate");
		
//		ticketValidator.validateUpdate(1, 2, "new", "new update check");
		
		ticketValidator.validateClose(2,1);

	}
}
