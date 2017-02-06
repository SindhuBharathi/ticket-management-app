package com.revature.validator;

import com.revature.exception.PersistenceException;
import com.revature.exception.ValidatorException;

public class TestLoginValidator {

	public static void main(String[] args) throws ValidatorException, PersistenceException {
		LoginValidator loginValidator=new LoginValidator();
		loginValidator.validateLogin("abaaa@gmail.com", "aaapwd");
		}

}
