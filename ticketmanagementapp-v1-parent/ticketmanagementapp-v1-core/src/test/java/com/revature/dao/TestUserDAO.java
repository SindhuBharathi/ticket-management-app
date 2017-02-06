package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.User;

public class TestUserDAO {

	public static void main(String[] args) throws PersistenceException {
		User u = new User();
		UserDAO userDAO = new UserDAO();

		u.setName("AAA"); 
		u.setEmailId("aaa@gmail.com");
		u.setPassword("aaa"); 
		userDAO.save(u);

/*		u.setEmailId("abaaa@gmail.com"); 
		u.setPassword("aaapwd");
		userDAO.updatePassword(u);
		
		userDAO.updateIsActive(2);
*/
		List<User> list = userDAO.listAll();
		for (User user : list)
			System.out.println(user);

		u = userDAO.listOneById(1);
		System.out.println(u);

		u = userDAO.listOneByName("AAA");
		System.out.println(u);

	}

}
