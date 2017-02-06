package com.revature.model;

import java.time.LocalDateTime;

import lombok.Data;
@ Data
public class TicketList {
	private Integer id;
	private String subject;
	private String description;
	private Department departmentId;
	private Priority priorityId;
	private LocalDateTime createdDateTime;
	private LocalDateTime resolvedDateTime;
	private String status;

}
