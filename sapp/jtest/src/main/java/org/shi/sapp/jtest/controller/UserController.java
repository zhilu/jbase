package org.shi.sapp.jtest.controller;

import javax.annotation.Resource;

import org.shi.sapp.jtest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/home")
	public String home() {
		userService.getUser();
		System.out.println("home");
		return "index";
	}
}
