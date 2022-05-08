package org.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.dal.BaseMapper;
import org.dal.mapper.UsersMapper;
import org.dal.bo.Users;
import org.biz.AbstractBaseService;
import org.biz.service.UsersService;


/**
 * @author shi
 */
 
@Service("usersService")
public class UsersServiceImpl extends AbstractBaseService<Users, Long> implements UsersService {
	
   
    @Resource
    private UsersMapper usersMapper;

	@Override
	public BaseMapper<Users, Long> getMapper() {
		return usersMapper;
	}
	
}