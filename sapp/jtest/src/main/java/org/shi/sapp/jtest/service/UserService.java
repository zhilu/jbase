package org.shi.sapp.jtest.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Resource
	private RoleService roleService;
	
	public String getUser() {
		String role = roleService.getRole();
		System.out.println("service"+role);
		return role;
	}

}
