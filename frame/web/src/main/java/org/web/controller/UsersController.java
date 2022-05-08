package org.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.biz.service.UsersService;
import org.dal.bo.Users;

 /**
 * @author shi
 */
@Controller
public class UsersController extends BaseController {

	@Resource
	private UsersService usersService;
	
	@RequestMapping(value="/hello.htm",method={RequestMethod.POST})
	@ResponseBody
	public Users index(){
		return usersService.findByPrimary(1L);
	}
	@RequestMapping(value="/home",method={RequestMethod.GET})
	@RequiresRoles(value={"admin"})
	public String home(){
		return "home";
	}
	@RequestMapping(value="/index",method={RequestMethod.GET})
	public String ide(){
		return "index";
	}
}