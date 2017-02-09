package com.revature.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.exception.ServiceException;
import com.revature.model.TicketList;
import com.revature.service.RegisterService;
import com.revature.service.UserLoginService;
import com.revature.service.UserService;

@Controller
@RequestMapping("/home")

public class UserController {
	UserLoginService userLoginService = new UserLoginService();
	RegisterService userRegisterService = new RegisterService();
	UserService userService = new UserService();

	@GetMapping("/login")
	public String login(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap) {
		try {
			userLoginService.login(emailId, password);
			return "../user.jsp";

		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../userLogin.jsp";
		}
	}

	@GetMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("emailId") String emailId,
			@RequestParam("password") String password, ModelMap modelMap) {
		try {
			userRegisterService.registerUser(name, emailId, password);
			return "../userLogin.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../register.jsp";
		}
	}

	@GetMapping("/create")
	public String create(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("subject") String subject, @RequestParam("description") String description,
			@RequestParam("department") String department, @RequestParam("priority") String priority,
			ModelMap modelMap) {
		try {
			String msg=userService.create(emailId, password, subject, description, department, priority);
			modelMap.addAttribute("MESSAGE", msg);
			return "../thanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../user.jsp";
		}
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("ticketId") int ticketId, @RequestParam("description") String description,
			ModelMap modelMap) {
		try {
			String msg=userService.update(emailId, password, ticketId, description);
			modelMap.addAttribute("MESSAGE", msg);
			return "../thanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../user.jsp";
		}
	}
	
	@GetMapping("/close")
	public String close(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("ticketId") int ticketId, ModelMap modelMap) {
		try {
			String msg=userService.close(emailId, password, ticketId);
			modelMap.addAttribute("MESSAGE", msg);
			return "../thanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../user.jsp";
		}
	}

	@GetMapping("/userViewTickets")
	public String view(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap) {
		try {
			List<TicketList> i = userService.view(emailId, password);
			modelMap.addAttribute("list", i);
			return "../userViewTicket.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../user.jsp";
		}
	}
	
}