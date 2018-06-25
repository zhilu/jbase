package ald.rc.biz.impl;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;

import ald.rc.biz.Main;
import ald.rc.biz.UserService;
import ald.rc.dal.bo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/service-beans.xml" })
public class TestUserService {
	static {
		PropertyConfigurator.configure(Main.class.getClassLoader().getResource("config/properties/log4j.properties"));
	}
	private static final Logger logger = Logger.getLogger(Main.class);

	@Autowired
	protected ApplicationContext ctx;
	@Autowired
	private UserService userService;
	
	@Test
	public void testApplicationContext() {
		Assert.assertNotNull(ctx);
		logger.info(ctx);
		logger.info(userService);
	}
	@Test
	public void testPage(){
		PageInfo<User> users =  userService.page(1, 10);
		logger.info(users);
	}
	
}
