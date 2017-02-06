package com.revature.model;

import lombok.Data;

@Data
public class Employee {
	private Integer id;
	private String name;
	private Department departmentId;
	private Role roleId;
	private String emailId;
	private String password;
	private Boolean active;

}
