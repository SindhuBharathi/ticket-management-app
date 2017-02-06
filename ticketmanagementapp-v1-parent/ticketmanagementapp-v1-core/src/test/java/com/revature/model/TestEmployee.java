package com.revature.model;

public class TestEmployee {
	public static void main(String args[]) {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("AAA");
		employee.setEmailId("aaa@gmail.com");
		employee.setPassword("aaa");

		System.out.println(employee);
	}
}
