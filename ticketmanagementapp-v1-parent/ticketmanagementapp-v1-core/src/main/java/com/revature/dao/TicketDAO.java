package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.EmailException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Priority;
import com.revature.model.Ticket;
import com.revature.model.TicketList;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.MailUtil;

public class TicketDAO implements DAO<Ticket> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(UserDAO.class.getName());
	FunctionsDAO functionsDAO=new FunctionsDAO();

	@Override
	public void save(Ticket t) throws PersistenceException {

	}

	public int create(Ticket ticket) throws PersistenceException, EmailException {
		try {
			String sql = "insert into TICKETS (USER_ID,SUBJECT,DESCRIPTION,DEPARTMENT_ID,PRIORITY_ID) values (?,?,?,?,?)";
			Object[] params = { ticket.getUserId().getId(), ticket.getSubject(), ticket.getDescription(), ticket.getDepartmentId().getId(), ticket.getPriorityId().getId() };
			jdbcTemplate.update(sql, params);
			String sql1 = "select last_insert_id()";
			int ticketId=jdbcTemplate.queryForObject(sql1, Integer.class);
			int userId=functionsDAO.getUserIdFromTicketId(ticketId);
			int departmentId=functionsDAO.getDepartmentIdFomTicketId(ticketId);
			String sql2="select ID from EMPLOYEES where DEPARTMENT_ID=? and ROLE_ID=1";
			Object[] params2={ departmentId };
			int employeeId=jdbcTemplate.queryForObject(sql2, params2,Integer.class);
			String email=functionsDAO.getEmployeeEmailId(employeeId);
			MailUtil.sendSimpleMail(email, "UserId "+userId+" has created a ticket of Id "+ticketId);
			return ticketId;
		} catch (DataIntegrityViolationException e) {
			throw new PersistenceException("User is not registered", e);
		}
	}

	public void updateTicket(Ticket ticket) throws PersistenceException {
		try {
			String sql = "update TICKETS set DESCRIPTION=? where ID=? and USER_ID=?";
			Object[] params = { ticket.getDescription(), ticket.getId(), ticket.getUserId().getId() };
			jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "1 ticket is updated");
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Ticket id doen't exists", e);
		}
	}

	public void closeTicket(Ticket ticket) throws PersistenceException {
		try {
			String sql = "update TICKETS set STATUS='Closed' where ID=? and USER_ID=? and STATUS='Open'";
			Object[] params = { ticket.getId(), ticket.getUserId().getId() };
			jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "1 ticket is closed");
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Ticket id doen't exists", e);
		}
	}

	@Override
	public void updatePassword(Ticket ticket) throws PersistenceException {

	}

	@Override
	public void updateIsActive(int id) throws PersistenceException {
		try {
			String sql = "update TICKETS set ACTIVE=0 where ID=?";
			Object[] params = { id };
			jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "1 ticket is deleted");
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Ticket id doen't exists", e);
		}
	}

	@Override
	public void delete(int id) throws PersistenceException {

	}

	@Override
	public List<Ticket> listAll() {
		String sql = "select * from TICKETS";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	@Override
	public Ticket listOneById(int id) throws PersistenceException {
		try {
			String sql = "select ID,USER_ID,SUBJECT,DESCRIPTION,DEPARTMENT_ID,PRIORITY_ID,CREATED_DATETIME,ASSIGNED_EMPLOYEE_ID,STATUS from TICKETS where ID=?";
			Object[] params = { id };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Ticket id doen't exists", e);
		}
	}

	@Override
	public Ticket listOneByName(String name) throws PersistenceException {
		return null;
	}

	public List<TicketList> listByUserId(int id) throws PersistenceException {
		try {
			String sql = "select ID,SUBJECT,DESCRIPTION,DEPARTMENT_ID,PRIORITY_ID,CREATED_DATETIME,STATUS from TICKETS where USER_ID='" + id
					+ "'";
			return jdbcTemplate.query(sql, (rs, rowNo) -> {
				TicketList t = new TicketList();
				t.setId(rs.getInt("ID"));
				t.setSubject(rs.getString("SUBJECT"));
				t.setDescription(rs.getString("DESCRIPTION"));
				Department d=new Department();
				d.setId(rs.getInt("DEPARTMENT_ID"));
				t.setDepartmentId(d);
				Priority p=new Priority();
				p.setId(rs.getInt("PRIORITY_ID"));
				t.setPriorityId(p);
				t.setCreatedDateTime(rs.getTimestamp("CREATED_DATETIME").toLocalDateTime());
				t.setStatus(rs.getString("STATUS"));
				return t;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("User id doen't exists", e);
		}
	}
	
	public List<Ticket> listByEmployeeId(int id) throws PersistenceException {
		try {
			String sql = "select ID,USER_ID,SUBJECT,DESCRIPTION,DEPARTMENT_ID,PRIORITY_ID,CREATED_DATETIME,RESOLVED_DATETIME,STATUS from TICKETS where ACTIVE=1 and ASSIGNED_EMPLOYEE_ID='" + id
					+ "'";
			return jdbcTemplate.query(sql, (rs, rowNo) -> {
				Ticket t = new Ticket();
				t.setId(rs.getInt("ID"));
				User u=new User();
				u.setId(rs.getInt("USER_ID"));
				t.setUserId(u);
				t.setSubject(rs.getString("SUBJECT"));
				Department d=new Department();
				d.setId(rs.getInt("DEPARTMENT_ID"));
				t.setDepartmentId(d);
				Priority p=new Priority();
				p.setId(rs.getInt("PRIORITY_ID"));
				t.setPriorityId(p);
				t.setDescription(rs.getString("DESCRIPTION"));
				t.setCreatedDateTime(rs.getTimestamp("CREATED_DATETIME").toLocalDateTime());
				t.setResolvedDateTime(rs.getTimestamp("RESOLVED_DATETIME").toLocalDateTime());
				t.setStatus(rs.getString("STATUS"));
				return t;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Employee id doen't exists", e);
		}
	}

	private Ticket convert(ResultSet rs) throws SQLException {
		Ticket t = new Ticket();
		t.setId(rs.getInt("ID"));
		User u = new User();
		u.setId(rs.getInt("USER_ID"));
		t.setUserId(u);
		t.setSubject(rs.getString("SUBJECT"));
		t.setDescription(rs.getString("DESCRIPTION"));
		Department d=new Department();
		d.setId(rs.getInt("DEPARTMENT_ID"));
		t.setDepartmentId(d);
		Priority p=new Priority();
		p.setId(rs.getInt("PRIORITY_ID"));
		t.setPriorityId(p);		
		t.setCreatedDateTime(rs.getTimestamp("CREATED_DATETIME").toLocalDateTime());
		Employee e = new Employee();
		e.setId(rs.getInt("ASSIGNED_EMPLOYEE_ID"));
		t.setAssignedEmployeeId(e);
		t.setStatus(rs.getString("STATUS"));
		return t;
	}

}
