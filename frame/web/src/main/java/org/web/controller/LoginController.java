package org.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.BaseController;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value="/login.do")
	public String login(String userName,String password){
		System.out.println("here");
		return "test";
	}

}
