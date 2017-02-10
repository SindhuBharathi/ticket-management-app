package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.exception.ServiceException;
import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.model.TicketList;
import com.revature.model.User;
import com.revature.service.EmployeeLoginService;
import com.revature.service.EmployeeService;
import com.revature.service.RegisterService;
import com.revature.service.UserLoginService;
import com.revature.service.UserService;

@Controller
@RequestMapping("/home")

public class TicketController {
	UserLoginService userLoginService = new UserLoginService();
	RegisterService userRegisterService = new RegisterService();
	UserService userService = new UserService();
	EmployeeLoginService employeeLoginService=new EmployeeLoginService();
	EmployeeService employeeService=new EmployeeService();
	User user=new User();
	Employee employee=new Employee();

	@GetMapping("/userLogin")
	public String userLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap, HttpSession session) {
		try {
			userLoginService.login(emailId, password);
			user.setEmailId(emailId);
			user.setPassword(password);
			session.setAttribute("USER_LOGGED_IN", user);
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
	public String create(@RequestParam("subject") String subject, @RequestParam("description") String description,
			@RequestParam("department") String department, @RequestParam("priority") String priority,
			ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("USER_LOGGED_IN");
			String msg=userService.create(user.getEmailId(),user.getPassword(), subject, description, department, priority);
			modelMap.addAttribute("MESSAGE", msg);
			return "../userThanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../user.jsp";
		}
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("ticketId") int ticketId, @RequestParam("description") String description,
			ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("USER_LOGGED_IN");
			String msg=userService.update(user.getEmailId(),user.getPassword(), ticketId, description);
			modelMap.addAttribute("MESSAGE", msg);
			return "../userThanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../user.jsp";
		}
	}
	
	@GetMapping("/close")
	public String close(@RequestParam("ticketId") int ticketId, ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("USER_LOGGED_IN");
			String msg=userService.close(user.getEmailId(),user.getPassword(), ticketId);
			modelMap.addAttribute("MESSAGE", msg);
			return "../userThanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../user.jsp";
		}
	}

	@GetMapping("/userViewTickets")
	public String userView(ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("USER_LOGGED_IN");
			List<TicketList> i = userService.view(user.getEmailId(),user.getPassword());
			modelMap.addAttribute("list", i);
			return "../userViewTicket.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../user.jsp";
		}
	}
	
	@GetMapping("/userLogout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:../index.jsp";
	}
	
	@GetMapping("/employeeLogin")
	public String employeeLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap, HttpSession session) {
		try {
			employeeLoginService.login(emailId, password);
			employee.setEmailId(emailId);
			employee.setPassword(password);
			session.setAttribute("EMPLOYEE_LOGGED_IN", employee);
			return "../employee.jsp";

		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employeeLogin.jsp";
		}
	}
	
	@GetMapping("/assign")
	public String assign(@RequestParam("ticketId") int ticketId, ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("EMPLOYEE_LOGGED_IN");
			String msg=employeeService.assign(employee.getEmailId(), employee.getPassword(), ticketId);
			modelMap.addAttribute("MESSAGE", msg);
			return "../employeeThanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/reAssign")
	public String reAssign(@RequestParam("ticketId") int ticketId, @RequestParam("employeeId") int employeeId, ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("EMPLOYEE_LOGGED_IN");
			String msg=employeeService.reAssign(employee.getEmailId(),employee.getPassword(), ticketId, employeeId);
			modelMap.addAttribute("MESSAGE", msg);
			return "../employeeThanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/reply")
	public String reply(@RequestParam("ticketId") int ticketId, String solution, ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("EMPLOYEE_LOGGED_IN");
			String msg=employeeService.reply(employee.getEmailId(), employee.getPassword(), ticketId, solution);
			modelMap.addAttribute("MESSAGE", msg);
			return "../employeeThanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("ticketId") int ticketId, ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("EMPLOYEE_LOGGED_IN");
			String msg=employeeService.delete(employee.getEmailId(), employee.getPassword(), ticketId);
			modelMap.addAttribute("MESSAGE", msg);
			return "../employeeThanks.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/employeeViewTickets")
	public String employeeView(ModelMap modelMap, HttpSession session) {
		try {
			session.getAttribute("EMPLOYEE_LOGGED_IN");
			List<Ticket> i = employeeService.view(employee.getEmailId(),employee.getPassword());
			modelMap.addAttribute("list", i);
			return "../employeeViewTicket.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "../employee.jsp";
		}
	}
	
	@GetMapping("/employeeLogout")
	public String employeeLogout(HttpSession session) {
		session.invalidate();
		return "redirect:../index.jsp";
	}

}