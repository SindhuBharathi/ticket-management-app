package com.revature.model;

import java.time.LocalDateTime;

public class TestTicket {

	public static void main(String[] args) {
		Ticket t = new Ticket();
		t.setId(1);
		User u = new User();
		u.setId(1);
		t.setUserId(u);
		t.setSubject("Test");
		t.setDescription("Sample test");
		t.setCreatedDateTime(LocalDateTime.parse("2017-02-01T10:15:30"));
		t.setStatus("open");
		Employee e=new Employee();
		e.setId(1);
		t.setAssignedEmployeeId(e);
		t.setResolvedDateTime(LocalDateTime.parse("2017-02-01T10:15:30"));
		System.out.println(t);
	}

}
