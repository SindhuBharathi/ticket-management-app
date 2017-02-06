package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;
import com.revature.model.Priority;
import com.revature.model.Ticket;
import com.revature.model.User;

public class TestTicketDAO {

	public static void main(String[] args) throws PersistenceException {
		Ticket t = new Ticket();
		TicketDAO ticketDAO = new TicketDAO();
		
		User u=new User();
		u.setId(1);
		t.setUserId(u);
		t.setSubject("version 3");
		t.setDescription("test for DAO");
		Department d=new Department();
		d.setId(1);
		t.setDepartmentId(d);
		Priority p=new Priority();
		p.setId(1);
		t.setPriorityId(p);
		System.out.println(ticketDAO.create(t));
/*		
		t.setId(2);
		t.setDescription("sample test - updated");
		User u=new User();
		u.setId(1);
		t.setUserId(u);
		ticketDAO.updateTicket(t);
		
		t.setId(2);
		User u=new User();
		u.setId(1);
		t.setUserId(u);
		ticketDAO.closeTicket(t);
	
*/		List<Ticket> list = ticketDAO.listAll();
		for (Ticket tck : list)
			System.out.println(tck);
		
		t = ticketDAO.listOneById(2);
		System.out.println(t);
		
//		System.out.println(ticketDAO.listByUserId(2));
		
/*		List<Ticket> list1 = ticketDAO.listByEmployeeId(2);
		for (Ticket tck : list1)
			System.out.println(tck);
*/		
	}

}
