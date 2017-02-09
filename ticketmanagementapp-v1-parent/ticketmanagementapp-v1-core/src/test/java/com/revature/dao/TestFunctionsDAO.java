package com.revature.dao;

import com.revature.exception.PersistenceException;

public class TestFunctionsDAO {
	public static void main(String args[]) throws PersistenceException {
		FunctionsDAO functionsDAO = new FunctionsDAO();
		
/*		System.out.println(functionsDAO.getUserId("abaaa@gmail.com", "aaapwd"));
		
		System.out.println(functionsDAO.getUserIdFromTicketId(2));
		
		System.out.println(functionsDAO.getStatus(2));
*/		
/*		System.out.println(functionsDAO.getEmployeeId("aaab@gmail.com","aaa"));
		
		System.out.println(functionsDAO.getEmployeeIdFromTicketId(1));
		
*/
/*		System.out.println(functionsDAO.getDepartmentId("HR"));
		
		System.out.println(functionsDAO.getPriorityId("Low"));
*/	
		System.out.println(functionsDAO.getEmployeeEmailId(5));
		
		System.out.println(functionsDAO.getUserEmailId(5));
	}
}
