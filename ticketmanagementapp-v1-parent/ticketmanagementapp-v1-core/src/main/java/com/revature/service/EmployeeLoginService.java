package com.revature.service;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.revature.dao.LoginEmployeeDAO;
import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidatorException;
import com.revature.validator.LoginValidator;

public class EmployeeLoginService {
	LoginValidator loginValidator = new LoginValidator();
	LoginEmployeeDAO loginDAO = new LoginEmployeeDAO();

	public Boolean login(String emailId, String password) throws ServiceException {
		try {
			Logger logger = Logger.getLogger(UserLoginService.class.getName());
			loginValidator.validateLogin(emailId, password);
			String name=loginDAO.login(emailId, password);
			logger.log(Level.INFO, "Login success... welcome "+name);
			return true;
		} catch (ValidatorException e) {
			throw new ServiceException("Login failure ", e);
		} catch (PersistenceException e) {
			throw new ServiceException("Invalid login ", e);
		}

	}


}
