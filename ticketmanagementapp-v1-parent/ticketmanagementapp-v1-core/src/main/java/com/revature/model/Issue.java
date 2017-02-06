package com.revature.model;

import lombok.Data;

@Data
public class Issue {
	private Integer id;
	private Ticket ticketId;
	private String solution;

}
