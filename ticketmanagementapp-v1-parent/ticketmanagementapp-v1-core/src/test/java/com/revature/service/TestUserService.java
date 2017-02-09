package com.revature.service;

import java.util.List;

import com.revature.exception.ServiceException;
import com.revature.model.TicketList;

public class TestUserService {

	public static void main(String[] args) throws ServiceException {
		UserService userService = new UserService();
		userService.create("abaaa@gmail.com", "aaa", "web", "web", "HR", "Low");
//		 userService.update("abaaa@gmail.com", "aaapwd", 10,"abaaa@gmail.com", "aaapwd");
		// userService.close("abaaa@gmail.com", "aaapwd", 8);
		List<TicketList> list=userService.view("aaa@gmail.com", "aaa");
		for(TicketList t:list)
		{
			System.out.println(t);
		}
	}

}
