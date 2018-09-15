package org.shi.sapp.jtest.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.test4j.junit.Test4J;
import org.test4j.module.spring.annotations.SpringBeanFrom;
import org.test4j.module.spring.annotations.SpringContext;

import mockit.Mocked;

@SpringContext("classpath:applicationContext.xml")
public class UserServiceTest extends Test4J{

	@Resource
	private UserService userService; 
	
	@Mocked
	@SpringBeanFrom
	private RoleService roleService;
	
	@Test
	public void getUserTest() {
		new Expectations() {
			{
				roleService.getRole();
				result ="xxx";
			}
		};
		String user = userService.getUser();
		System.out.println(user);
		want.string(user).eq("xxx");
		
	}
}
