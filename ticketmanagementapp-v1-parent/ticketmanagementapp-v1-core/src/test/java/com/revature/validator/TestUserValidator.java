package com.revature.validator;

import com.revature.exception.PersistenceException;
import com.revature.exception.ValidatorException;

public class TestUserValidator {
	public static void main(String[] args) throws ValidatorException, PersistenceException {
		RegisterValidator userValidator=new RegisterValidator();
		userValidator.validateSave("aaa@gmail.com", "pwd", "BBB");

		}

}
