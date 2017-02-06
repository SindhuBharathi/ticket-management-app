package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Role;

public class TestRoleDAO {

	public static void main(String[] args) throws PersistenceException {
		Role role = new Role();
		RoleDAO roleDAO = new RoleDAO();
		role.setName("Employee");
		roleDAO.save(role);

		List <Role> list=roleDAO.listAll();
		for(Role r:list)
			System.out.println(r);
	}

}
