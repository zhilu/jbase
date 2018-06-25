package ald.rc.dal.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ald.rc.dal.Main;
import ald.rc.dal.bo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/dao-beans.xml"})
public class UserMapperTest {
	static {
		PropertyConfigurator.configure(Main.class.getClassLoader().getResource("config/properties/log4j.properties"));
	}
	
	private static final Logger logger =Logger.getLogger(UserMapperTest.class);

	@Autowired
	protected ApplicationContext ctx;

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testApplicationContext() {
		Assert.assertNotNull(ctx);
	}

	@Test
	public void testMapper() {
		User user = userMapper.findByPrimary(8871L);
		logger.info(user.getPhone());
		Assert.assertEquals(user.getConsumerno(), "97");
	}
	
	@Test
	public void testPage(){
		List<User> users = userMapper.listSelective(new HashMap<String,String>());
		logger.info(users.size());
		Assert.assertNotNull(users);
	}

}