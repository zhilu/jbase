package ald.rc.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ald.rc.web.core.BaseController;

@Controller
public class UserController extends BaseController {

	private static final Logger logger =LogManager.getLogger(UserController.class);
	
	@RequestMapping(value="/test")
	public String hello() throws Exception{
		logger.info("hello");
		String x = "1";
		if("1".equals(x)){
			throw new Exception();
		}
		
		return "success";
	}
}
