package com.revature.util;

import com.revature.exception.ValidatorException;

public class ValidatorUtil {
	public void rejectIfNullOrEmpty(String input, String message) throws ValidatorException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidatorException("Invalid " + message);
		}
	}

	public void rejectIfNegativeOrZeroOrNull(Integer input, String message) throws ValidatorException {
		if (input == null || input <= 0) {
			throw new ValidatorException("Invalid " + message);
		}
	}

	public void rejectIfNotEqual(Integer input1, Integer input2, String message) throws ValidatorException {
		if (input1 != input2) {
			throw new ValidatorException(message + " doesn't match");
		}
	}
	
	public void rejectIfEqual(Integer input1, Integer input2, String message) throws ValidatorException {
		if (input1 == input2) {
			throw new ValidatorException(message + " same Employee Id");
		}
	}

	public void rejectIfStatusClosed(String input, String message) throws ValidatorException {
		if ("Closed".equals(input)) {
			throw new ValidatorException(message + " closed already");
		}
	}
	
	public void rejectIfStatusOpen(String input, String message) throws ValidatorException {
		if ("Open".equals(input)) {
			throw new ValidatorException(message + " open status");
		}
	}
	
	public void rejectIfStatusNotOpen(String input, String message) throws ValidatorException {
		if ("Progress".equals(input) || "Resolved".equals(input)) {
			throw new ValidatorException(message + " not open status");
		}
	}
	
}
