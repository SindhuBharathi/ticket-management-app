package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidatorException;
import com.revature.model.User;
import com.revature.validator.RegisterValidator;

public class RegisterService {
	RegisterValidator registerValidator = new RegisterValidator();
	UserDAO userDAO = new UserDAO();

	public void registerUser(String name, String emailId, String password) throws ServiceException {
		try {
			registerValidator.validateSave(emailId, password, name);
			User u = new User();
			u.setName(name);
			u.setEmailId(emailId);
			u.setPassword(password);
			userDAO.save(u);
		} catch (PersistenceException e) {
			throw new ServiceException("EMailId is registered already", e);
		} catch (ValidatorException e) {
			throw new ServiceException("Invalid input", e);
		}
	}

}
