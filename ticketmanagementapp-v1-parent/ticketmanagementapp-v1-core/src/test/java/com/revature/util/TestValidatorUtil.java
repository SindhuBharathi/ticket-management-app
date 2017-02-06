package com.revature.util;

import com.revature.exception.ValidatorException;

public class TestValidatorUtil {

	public static void main(String[] args) throws ValidatorException {
		ValidatorUtil validatorUtil = new ValidatorUtil();
//		validatorUtil.rejectIfNullOrEmpty("", "input");
//		validatorUtil.rejectIfNegativeOrZeroOrNull(0, "invalid");
//		validatorUtil.rejectIfNotEqual(1, 2, "not equal");
//		validatorUtil.rejectIfEqual(1, 1, "same");
		validatorUtil.rejectIfStatusOpen("Open", "not in progress");
	}

}
