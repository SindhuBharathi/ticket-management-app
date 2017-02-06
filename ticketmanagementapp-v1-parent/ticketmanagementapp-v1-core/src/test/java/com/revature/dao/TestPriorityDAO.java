package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Priority;

public class TestPriorityDAO {

	public static void main(String[] args) throws PersistenceException {
		Priority priority = new Priority();
		PriorityDAO priorityDAO = new PriorityDAO();
		priority.setName("Low");
  		priorityDAO.save(priority);
 
		List<Priority> list = priorityDAO.listAll();
		for (Priority p : list)
			System.out.println(p);
	}

}
