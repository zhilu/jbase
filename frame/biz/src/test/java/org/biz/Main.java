package org.biz;

import org.biz.service.UsersService;
import org.dal.bo.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{
    private static final Logger logger =LoggerFactory.getLogger(Main.class);
    
    public static ClassPathXmlApplicationContext ctx = null;
    static{
    	ctx = new ClassPathXmlApplicationContext("config/spring/service-bean.xml");
    }
    
    public static void main(String [] args){
    	testService();
    	logger.info("start");
    }
    
    public static void testService(){
    	UsersService usersService = (UsersService) ctx.getBean("usersService");
    	Users user = usersService.findByPrimary(1L);
    	logger.info(user.toString());
    }
    
    
}
