package com.revature.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.exception.ServiceException;
import com.revature.model.Ticket;
import com.revature.model.TicketList;
import com.revature.service.EmployeeLoginService;
import com.revature.service.EmployeeService;
import com.revature.service.RegisterService;
import com.revature.service.UserLoginService;
import com.revature.service.UserService;

@Controller
@RequestMapping("/home")

public class UserController {
	UserLoginService userLoginService = new UserLoginService();
	RegisterService userRegisterService = new RegisterService();
	UserService userService = new UserService();
	EmployeeLoginService employeeLoginService=new EmployeeLoginService();
	EmployeeService employeeService=new EmployeeService();

	@GetMapping("/userLogin")
	public String userLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
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
	public String userView(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
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
	
	@GetMapping("/employeeLogin")
	public String employeeLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap) {
		try {
			employeeLoginService.login(emailId, password);
			return "../employee.jsp";

		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employeeLogin.jsp";
		}
	}
	
	@GetMapping("/assign")
	public String assign(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("ticketId") int ticketId, ModelMap modelMap) {
		try {
			String msg=employeeService.assign(emailId, password, ticketId);
			modelMap.addAttribute("MESSAGE", msg);
			return "../thanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/reAssign")
	public String reAssign(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("ticketId") int ticketId, @RequestParam("employeeId") int employeeId, ModelMap modelMap) {
		try {
			String msg=employeeService.reAssign(emailId, password, ticketId, employeeId);
			modelMap.addAttribute("MESSAGE", msg);
			return "../thanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/reply")
	public String reply(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("ticketId") int ticketId, String solution, ModelMap modelMap) {
		try {
			String msg=employeeService.reply(emailId, password, ticketId, solution);
			modelMap.addAttribute("MESSAGE", msg);
			return "../thanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("ticketId") int ticketId, ModelMap modelMap) {
		try {
			String msg=employeeService.delete(emailId, password, ticketId);
			modelMap.addAttribute("MESSAGE", msg);
			return "../thanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/employeeViewTickets")
	public String employeeView(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap) {
		try {
			List<Ticket> i = employeeService.view(emailId, password);
			modelMap.addAttribute("list", i);
			return "../employeeViewTicket.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}

}