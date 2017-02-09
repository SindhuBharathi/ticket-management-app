package com.revature.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.util.ConnectionUtil;

public class FunctionsDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public int getUserId(String emailId, String password) {
		String sql = "select ID from USERS where EMAIL_ID=? and PASSWORD=?";
		Object[] params = { emailId, password };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}

	public int getUserIdFromTicketId(int ticketId) {
		String sql = "select USER_ID from TICKETS where ID=?";
		Object[] params = { ticketId };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	public String getUserEmailId(int userId) {
		String sql = "select EMAIL_ID from USERS where ID=?";
		Object[] params = { userId };
		return jdbcTemplate.queryForObject(sql, params, String.class);
	}

	public String getStatus(int ticketId) {
		String sql = "select STATUS from TICKETS where ID=?";
		Object[] params = { ticketId };
		return jdbcTemplate.queryForObject(sql, params, String.class);
	}
	
	public int getEmployeeId(String emailId, String password) {
		String sql = "select ID from EMPLOYEES where EMAIL_ID=? and PASSWORD=?";
		Object[] params = { emailId, password };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}

	public int assignEmployeeId(int departmentId) throws PersistenceException {
		try {
			String sql = "select ID from EMPLOYEES where DEPARTMENT_ID=? limit 1";
			Object[] params={ departmentId };
			return (int) jdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Employee unavailable", e);
		}
	}
	
	public int getEmployeeIdFromTicketId(int ticketId) {
		String sql = "select ASSIGNED_EMPLOYEE_ID from TICKETS where ID=?";
		Object[] params = { ticketId };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	public int getEmployeeRoleId(int employeeId) {
		String sql = "select ROLE_ID from EMPLOYEES where ID=?";
		Object[] params = { employeeId };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	public int getEmployeeDepartmentId(int employeeId) {
		String sql = "select DEPARTMENT_ID from EMPLOYEES where ID=?";
		Object[] params = { employeeId };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	public String getEmployeeEmailId(int employeeId) {
		String sql = "select EMAIL_ID from EMPLOYEES where ID=?";
		Object[] params = { employeeId };
		return jdbcTemplate.queryForObject(sql, params, String.class);
	}
	
	public int getDepartmentIdFomTicketId(int ticketId) {
		String sql = "select DEPARTMENT_ID from TICKETS where ID=?";
		Object[] params = { ticketId };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	public int getDepartmentId(String name) {
		String sql = "select ID from DEPARTMENTS where NAME=?";
		Object[] params = { name };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
	
	public int getPriorityId(String name) {
		String sql = "select ID from PRIORITIES where NAME=?";
		Object[] params = { name };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}

}
