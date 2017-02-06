package com.revature.validator;

import com.revature.exception.ValidatorException;
import com.revature.util.ValidatorUtil;

public class RegisterValidator {
	ValidatorUtil validatorUtil = new ValidatorUtil();

	public void validateSave(String emailId, String password, String name) throws ValidatorException /*, PersistenceException*/ {
		validatorUtil.rejectIfNullOrEmpty(emailId, "EmailId");
		validatorUtil.rejectIfNullOrEmpty(password, "Password");
		validatorUtil.rejectIfNullOrEmpty(name, "Name");
		
/*		 Logger logger = Logger.getLogger(RegisterValidator.class.getName());
		 UserDAO userDAO=new UserDAO(); 
		 User u=new User();
		 u.setName(name);
		 u.setEmailId(emailId);
		 u.setPassword(password);
		 userDAO.save(u);
		 logger.log(Level.INFO, "Registration success... welcome "+name);
*/		  
	}
}
