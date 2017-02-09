package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.EmailException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Role;
import com.revature.util.ConnectionUtil;
import com.revature.util.MailUtil;

public class EmployeeDAO implements DAO<Employee> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(EmployeeDAO.class.getName());
	FunctionsDAO functionsDAO = new FunctionsDAO();
	@Override
	public void save(Employee employee) throws PersistenceException {
		try {
			String sql = "insert into EMPLOYEES (NAME,DEPARTMENT_ID,ROLE_ID,EMAIL_ID,PASSWORD) values (?,?,?,?,?)";
			Object[] params = { employee.getName(), employee.getDepartmentId().getId(), employee.getRoleId().getId(), employee.getEmailId(), employee.getPassword() };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Employee details inserted %d", rows);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Same EmailId given", e);
		} catch (DataIntegrityViolationException e) {
			throw new PersistenceException("Foreign key constraint fails", e);
		}
	}

	@Override
	public void updatePassword(Employee employee) throws PersistenceException {
		try {
			String sql = "update EMPLOYEES set PASSWORD=? where EMAIL_ID=?";
			Object[] params = { employee.getPassword(), employee.getEmailId() };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Employee password changed %d", rows);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("EmailId not exists", e);
		}
	}

	@Override
	public void updateIsActive(int id) throws PersistenceException {
		try {
			String sql = "update EMPLOYEES set ACTIVE=0 where ID=?";
			Object[] params = { id };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Employees made inactive %d", rows);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Id not exists", e);
		}

	}

	@Override
	public List<Employee> listAll() {
		String sql = "select ID,NAME,DEPARTMENT_ID,ROLE_ID,EMAIL_ID,PASSWORD,ACTIVE from EMPLOYEES";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	@Override
	public Employee listOneById(int id) throws PersistenceException {
		try {
			String sql = "select ID,NAME,DEPARTMENT_ID,ROLE_ID,EMAIL_ID,PASSWORD from EMPLOYEES where ID=?";
			Object[] params = { id };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Employee id doen't exists", e);
		}
	}

	@Override
	public Employee listOneByName(String name) throws PersistenceException {
		try {
			String sql = "select ID,NAME,DEPARTMENT_ID,ROLE_ID,EMAIL_ID,PASSWORD from EMPLOYEES where NAME=?";
			Object[] params = { name };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Employee name doen't exists", e);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new PersistenceException("more records", e);
		}

	}

	private Employee convert(ResultSet rs) throws SQLException {
		Employee e = new Employee();
		e.setId(rs.getInt("ID"));
		e.setName(rs.getString("NAME"));
		Department d=new Department();
		d.setId(rs.getInt("DEPARTMENT_ID"));
		e.setDepartmentId(d);
		Role r=new Role();
		r.setId(rs.getInt("ROLE_ID"));
		e.setRoleId(r);
		e.setEmailId(rs.getString("EMAIL_ID"));
		e.setPassword(rs.getString("PASSWORD"));
		return e;
	}

	@Override
	public void delete(int id) throws PersistenceException {

	}

	public void assignEmployee(int ticketId) throws PersistenceException, EmailException {
		try {
			int employeeId = functionsDAO.assignEmployeeId(functionsDAO.getDepartmentIdFomTicketId(ticketId));
			String sql = "update TICKETS set ASSIGNED_EMPLOYEE_ID=?,STATUS='Progress' where ID=? and STATUS='Open'";
			Object[] params = { employeeId, ticketId };
			jdbcTemplate.update(sql, params);
			String employeeEmail=functionsDAO.getEmployeeEmailId(employeeId);
			int userId=functionsDAO.getUserIdFromTicketId(ticketId);
			MailUtil.sendSimpleMail(employeeEmail, "User id "+userId+" created ticket with id "+ticketId);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("TicketId not exists", e);
		}
	}

	public void assignEmployee(int ticketId, int employeeId) throws PersistenceException {
		try {
			String sql = "update TICKETS set ASSIGNED_EMPLOYEE_ID=? where ID=? and STATUS='Progress'";
			Object[] params = { employeeId, ticketId };
			jdbcTemplate.update(sql, params);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("TicketId not exists", e);
		}
	}

	public void employeeReply(int ticketId, String solution) throws PersistenceException, EmailException {
		try {
			String sql = "update TICKETS set STATUS='Resolved', RESOLVED_DATETIME=now() where ID=? and STATUS='Progress'";
			Object[] params = { ticketId };
			jdbcTemplate.update(sql, params);
			String sql2 = "insert into ISSUES (TICKET_ID,SOLUTION) values (?,?)";
			Object[] params2 = { ticketId, solution };
			jdbcTemplate.update(sql2, params2);
			int userId=functionsDAO.getUserIdFromTicketId(ticketId);
			String email=functionsDAO.getUserEmailId(userId);
			MailUtil.sendSimpleMail(email,"Ticket id "+ticketId+" has got a reply. Solution: "+solution);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("TicketId not exists", e);
		}
	}

}
