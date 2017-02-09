package com.revature.dao;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Role;

public class TestEmployeeDAO {
	public static void main(String[] args) throws PersistenceException, EmailException {
		Employee e=new Employee();
		EmployeeDAO employeeDAO=new EmployeeDAO();
	
		e.setName("BBB");
		e.setEmailId("bbbaa@gmail.com");
		e.setPassword("aaa");
		Role r=new Role();
		r.setId(2);
		e.setRoleId(r);
		Department d=new Department();
		d.setId(2);
		e.setDepartmentId(d);
		employeeDAO.save(e);

		
		e.setEmailId("aaa@gmail.com");
		e.setPassword("aaapwd");
		employeeDAO.updatePassword(e);
		

		List<Employee> list=employeeDAO.listAll();
		for(Employee employee:list)
			System.out.println(employee);

		e=employeeDAO.listOneById(3);
		System.out.println(e);

//		e=employeeDAO.listOneByName("AAA");
//		System.out.println(e);
		
		employeeDAO.assignEmployee(12);
		
//		employeeDAO.assignEmployee(2, 3);
		
		employeeDAO.employeeReply(3, "solution");
		
	}
}
