package com.revature.validator;

import com.revature.exception.PersistenceException;
import com.revature.exception.ValidatorException;

public class TestEmployeeValidator {

	public static void main(String[] args) throws ValidatorException, PersistenceException {
		EmployeeValidator employeeValidator = new EmployeeValidator();
//		employeeValidator.validateAssignEmployee(2);
//		employeeValidator.validateAssignEmployee(1, 3);
		employeeValidator.validateEmployeeReply(1,3, "test soln");
	}

}
