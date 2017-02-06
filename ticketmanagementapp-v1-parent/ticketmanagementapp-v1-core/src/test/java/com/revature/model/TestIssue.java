package com.revature.model;

public class TestIssue {

	public static void main(String[] args) {
		Issue i = new Issue();
		i.setId(1);
		Ticket t=new Ticket();
		t.setId(1);
		i.setTicketId(t);
		i.setSolution("sample");

		System.out.println(i);
	}

}
