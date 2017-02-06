package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;

public class TestDepartmentDAO {

	public static void main(String[] args) throws PersistenceException {
		Department department = new Department();
		DepartmentDAO departmentDAO = new DepartmentDAO();
		department.setName("Finance");
		departmentDAO.save(department);
		
		System.out.println(departmentDAO.listOneById(1));
		System.out.println(departmentDAO.listOneByName("HR"));

		List<Department> list=departmentDAO.listAll();
		for(Department d:list)
		{
			System.out.println(d);
		}
	}

}
