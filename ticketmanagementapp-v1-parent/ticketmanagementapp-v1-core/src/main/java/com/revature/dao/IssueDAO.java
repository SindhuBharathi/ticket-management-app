package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Issue;
import com.revature.model.Ticket;
import com.revature.util.ConnectionUtil;

public class IssueDAO implements DAO<Issue> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(Issue t) throws PersistenceException {

	}

	@Override
	public void updatePassword(Issue t) throws PersistenceException {

	}

	@Override
	public void updateIsActive(int id) throws PersistenceException {

	}

	@Override
	public List<Issue> listAll() {
		String sql = "select ID,TICKET_ID,SOLUTION from ISSUES";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	@Override
	public Issue listOneById(int id) throws PersistenceException {
		try {
			String sql = "select ID,TICKET_ID,SOLUTION from ISSUES where ID=?";
			Object[] params = { id };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Issue id doen't exists", e);
		}
	}

	@Override
	public Issue listOneByName(String name) throws PersistenceException {
		return null;
	}

	public List<Map<String, Object>> listByTicketId(int id) throws PersistenceException {
		try {
			String sql = "select ID,TICKET_ID,SOLUTION from ISSUES where TICKET_ID=?";
			Object[] params = { id };
			return jdbcTemplate.queryForList(sql, params);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Issue id doen't exists", e);
		}
	}

	private Issue convert(ResultSet rs) throws SQLException {
		Issue i = new Issue();
		i.setId(rs.getInt("ID"));
		Ticket t = new Ticket();
		t.setId(rs.getInt("TICKET_ID"));
		i.setTicketId(t);
		i.setSolution("SOLUTION");
		return i;
	}

	@Override
	public void delete(int id) throws PersistenceException {
		
	}
}
