package com.revature.dao;

import com.revature.exception.PersistenceException;

public class TestLoginDAO {

	public static void main(String[] args) throws PersistenceException {
		LoginUserDAO l=new LoginUserDAO();
		System.out.println(l.login("abaaa@gmail.com", "aaa"));
		}

}
