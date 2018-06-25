package ald.rc.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ald.rc.biz.UserService;
import ald.rc.dal.BaseMapper;
import ald.rc.dal.bo.User;
import ald.rc.dal.mapper.UserMapper;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public BaseMapper<User, Long> getBaseMapper() {
		return userMapper;
	}

	@Override
	public PageInfo<User> page(int start, int end) {
		PageHelper.startPage(start, end);
	    List<User> list = userMapper.listSelective(null);
	    PageInfo<User> page = new PageInfo<User>(list);
	    return page;
	}
}
