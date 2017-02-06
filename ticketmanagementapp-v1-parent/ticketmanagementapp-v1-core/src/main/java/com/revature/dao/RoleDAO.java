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
import com.revature.model.Role;
import com.revature.util.ConnectionUtil;

public class RoleDAO implements DAO<Role> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(RoleDAO.class.getName());

	@Override
	public void save(Role role) throws PersistenceException {
		try {
			String sql = "insert into ROLES (NAME) values (?)";
			Object[] params = { role.getName() };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Roles inserted %d", rows);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Same name given", e);
		}

	}

	@Override
	public void updatePassword(Role t) throws PersistenceException {

	}

	@Override
	public void updateIsActive(int id) throws PersistenceException {
		try {
			String sql = "update ROLES set ACTIVE=0 where ID=?";
			Object[] params = { id };
			int rows = jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "No. of Roles made inactive %d", rows);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Id not exists", e);
		}

	}

	@Override
	public void delete(int id) throws PersistenceException {

	}

	@Override
	public List<Role> listAll() {
		String sql = "select ID,NAME from ROLES";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));

	}

	@Override
	public Role listOneById(int id) throws PersistenceException {
		try {
			String sql = "select ID,NAME from ROLES where ID=?";
			Object[] params = { id };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Priority id doen't exists", e);
		}

	}

	@Override
	public Role listOneByName(String name) throws PersistenceException {
		try {
			String sql = "select ID,NAME from ROLES where NAME=?";
			Object[] params = { name };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Priority name doen't exists", e);
		}

	}

	private Role convert(ResultSet rs) throws SQLException {
		Role r = new Role();
		r.setId(rs.getInt("ID"));
		r.setName(rs.getString("NAME"));
		return r;
	}

}
