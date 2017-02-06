package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Priority;
import com.revature.util.ConnectionUtil;

public class PriorityDAO implements DAO<Priority> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(PriorityDAO.class.getName());

	@Override
	public void save(Priority priority) throws PersistenceException {
		try {
			String sql = "insert into PRIORITIES (NAME) values (?)";
			Object[] params = { priority.getName() };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Priorities inserted %d", rows);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Same name given", e);
		}

	}

	@Override
	public void updatePassword(Priority t) throws PersistenceException {

	}

	@Override
	public void updateIsActive(int id) throws PersistenceException {
		try {
			String sql = "update PRIORITIES set ACTIVE=0 where ID=?";
			Object[] params = { id };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Priorities made inactive %d", rows);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Id not exists", e);
		}

	}

	@Override
	public void delete(int id) throws PersistenceException {

	}

	@Override
	public List<Priority> listAll() {
		String sql = "select ID,NAME from PRIORITIES";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));

	}

	@Override
	public Priority listOneById(int id) throws PersistenceException {
		try {
			String sql = "select ID,NAME from PRIORITIES where ID=?";
			Object[] params = { id };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Priority id doen't exists", e);
		}
	}

	@Override
	public Priority listOneByName(String name) throws PersistenceException {
		try {
			String sql = "select ID,NAME from PRIORITES where NAME=?";
			Object[] params = { name };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Priority name doen't exists", e);
		}
	}

	private Priority convert(ResultSet rs) throws SQLException {
		Priority p = new Priority();
		p.setId(rs.getInt("ID"));
		p.setName(rs.getString("NAME"));
		return p;
	}
}
