package com.revature.validator;

/*import java.util.logging.Level;
import java.util.logging.Logger;

import com.revature.dao.LoginDAO;
import com.revature.exception.PersistenceException;
*/
import com.revature.exception.ValidatorException;
import com.revature.util.ValidatorUtil;

public class LoginValidator {
	ValidatorUtil validatorUtil = new ValidatorUtil();

	public void validateLogin(String emailId, String password) throws ValidatorException /*, PersistenceException*/ {
		validatorUtil.rejectIfNullOrEmpty(emailId, "EmailId");
		validatorUtil.rejectIfNullOrEmpty(password, "Password");
		/*
		 * Logger logger = Logger.getLogger(LoginValidator.class.getName());
		 * LoginDAO loginDAO=new LoginDAO(); 
		 * String name=loginDAO.login(emailId,password); 
		 * logger.log(Level.INFO, "Login success... welcome "+name);
		 */ 
		}
}
