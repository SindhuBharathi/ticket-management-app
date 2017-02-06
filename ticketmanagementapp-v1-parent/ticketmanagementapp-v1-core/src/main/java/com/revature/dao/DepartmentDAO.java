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
import com.revature.model.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDAO implements DAO<Department> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(DepartmentDAO.class.getName());

	@Override
	public void save(Department department) throws PersistenceException {
		try {
			String sql = "insert into DEPARTMENTS (NAME) values (?)";
			Object[] params = { department.getName() };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Department details inserted %d", rows);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Same name given", e);
		}
	}

	@Override
	public void updatePassword(Department t) throws PersistenceException {

	}

	@Override
	public void updateIsActive(int id) throws PersistenceException {
		try {
			String sql = "update DEPARTMENTS set ACTIVE=0 where ID=?";
			Object[] params = { id };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Departments made inactive %d", rows);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Id not exists", e);
		}

	}

	@Override
	public void delete(int id) throws PersistenceException {

	}

	@Override
	public List<Department> listAll() {
		String sql = "select ID,NAME from DEPARTMENTS";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));

	}

	@Override
	public Department listOneById(int id) throws PersistenceException {
		try {
			String sql = "select ID,NAME from DEPARTMENTS where ID=?";
			Object[] params = { id };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Department id doen't exists", e);
		}

	}

	@Override
	public Department listOneByName(String name) throws PersistenceException {
		try {
			String sql = "select ID,NAME from DEPARTMENTS where NAME=?";
			Object[] params = { name };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Department name doen't exists", e);
		}
	}

	private Department convert(ResultSet rs) throws SQLException {
		Department d = new Department();
		d.setId(rs.getInt("ID"));
		d.setName(rs.getString("NAME"));
		return d;
	}
}
