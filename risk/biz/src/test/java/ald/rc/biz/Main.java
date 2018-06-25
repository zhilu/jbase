package ald.rc.biz;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	static {
		PropertyConfigurator.configure(Main.class.getClassLoader().getResource("config/properties/log4j.properties"));
	}
	private static final Logger logger = Logger.getLogger(Main.class);

	private static ApplicationContext ctx = null;

	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("config/service-beans.xml");
		logger.info(ctx);
		UserService userService =  ctx.getBean(UserService.class);
		userService =  (UserService) ctx.getBean("userService");
		logger.info(userService.page(1, 10).getList().get(1));
	}
}
