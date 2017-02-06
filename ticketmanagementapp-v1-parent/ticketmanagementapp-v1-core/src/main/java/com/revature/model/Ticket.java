package com.revature.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Ticket {
	private Integer id;
	private User userId;
	private String subject;
	private String description;
	private Department departmentId;
	private Priority priorityId;
	private LocalDateTime createdDateTime;
	private Employee assignedEmployeeId;
	private LocalDateTime resolvedDateTime;
	private String status;
	private Boolean active;
}
