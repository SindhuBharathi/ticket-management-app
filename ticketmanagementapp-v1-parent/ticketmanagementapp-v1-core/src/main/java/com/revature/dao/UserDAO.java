package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDAO implements DAO<User> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(UserDAO.class.getName());

	@Override
	public void save(User user) throws PersistenceException {
		try {
			String sql = "insert into USERS (NAME,EMAIL_ID,PASSWORD) values (?,?,?)";
			Object[] params = { user.getName(), user.getEmailId(), user.getPassword() };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of User details inserted %d", rows);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Same EmailId given", e);
		}
	}

	@Override
	public void updatePassword(User user) throws PersistenceException {
		try {
			String sql = "update USERS set PASSWORD=? where EMAIL_ID=?";
			Object[] params = { user.getPassword(), user.getEmailId() };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of User password changed %d", rows);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("EmailId not exists", e);
		}
	}

	@Override
	public void updateIsActive(int id) throws PersistenceException {
		try {
			String sql = "update USERS set ACTIVE=0 where ID=?";
			Object[] params = { id };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Users made inactive %d", rows);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("UserId not exists", e);
		}
	}

	@Override
	public void delete(int id) throws PersistenceException {
		try {
			String sql = "delete from USERS where ID=?";
			Object[] params = { id };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Users deleted %d", rows);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("UserId not exists", e);
		}
	}

	@Override
	public List<User> listAll() {
		String sql = "select ID,NAME,EMAIL_ID,PASSWORD,ACTIVE from USERS";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	@Override
	public User listOneById(int id) throws PersistenceException {
		try {
			String sql = "select ID,NAME,EMAIL_ID,PASSWORD,ACTIVE from USERS where ID=?";
			Object[] params = { id };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("User id doen't exists", e);
		}
	}

	@Override
	public User listOneByName(String name) throws PersistenceException {
		try {
			String sql = "select ID,NAME,EMAIL_ID,PASSWORD,ACTIVE from USERS where NAME=?";
			Object[] params = { name };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("User id doen't exists", e);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new PersistenceException("more records", e);
		}
	}

	private User convert(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("ID"));
		u.setName(rs.getString("NAME"));
		u.setEmailId(rs.getString("EMAIL_ID"));
		u.setPassword(rs.getString("PASSWORD"));
		u.setActive(rs.getBoolean("ACTIVE"));
		return u;
	}
}
