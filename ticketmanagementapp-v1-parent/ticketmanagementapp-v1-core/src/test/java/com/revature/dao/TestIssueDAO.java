package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Issue;

public class TestIssueDAO {

	public static void main(String[] args) throws PersistenceException {
		IssueDAO issueDAO=new IssueDAO();
		Issue i=new Issue();
		List <Issue> list=issueDAO.listAll();
		for(Issue issue: list)
			System.out.println(issue);
			
		i=issueDAO.listOneById(1);
		System.out.println(i);

		System.out.println(issueDAO.listByTicketId(1));

	}

}
