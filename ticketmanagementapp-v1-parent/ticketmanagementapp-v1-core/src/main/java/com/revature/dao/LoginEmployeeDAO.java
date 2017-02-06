package com.revature.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class LoginEmployeeDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	User user = new User();

	public String login(String emailId, String password) throws PersistenceException {
		try {
			String sql = "select NAME from EMPLOYEES where EMAIL_ID=? and PASSWORD=?";
			Object[] params = { emailId, password };
			return jdbcTemplate.queryForObject(sql, params, String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Incorrect EmailId or Password given", e);
		}

	}

}
