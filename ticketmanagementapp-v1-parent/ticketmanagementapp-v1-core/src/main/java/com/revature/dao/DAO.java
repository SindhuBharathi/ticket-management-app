package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;

public interface DAO<T> {
	void save(T t) throws PersistenceException;

	void updatePassword(T t) throws PersistenceException;

	void updateIsActive(int id) throws PersistenceException;

	void delete(int id) throws PersistenceException;

	List<T> listAll();

	T listOneById(int id) throws PersistenceException;

	T listOneByName(String name) throws PersistenceException;
}
