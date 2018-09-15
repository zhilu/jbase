package org.shi.sapp.jtest.service;

import org.springframework.stereotype.Service;

@Service
public class RoleService {

	public String getRole() {
		System.out.println("get user");
		return "user";
	}

}
