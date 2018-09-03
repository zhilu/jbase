package org.web;


import org.biz.service.UsersService;
import org.dal.bo.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger logger =LoggerFactory.getLogger(Main.class);
    
    public static ClassPathXmlApplicationContext ctx = null;
    static{
    	ctx = new ClassPathXmlApplicationContext("config/spring/root-bean.xml");
    }

    public static void main(String[] args) {
    	testDelete();
    	testInsert();
    	testUpdate();
        ctx.close();
    }
    
    public static void testInsert(){
    	logger.info("test insert start");
    	UsersService userService  = ctx.getBean(UsersService.class);
        Users user = new Users();
        user.setId(10L);
        user.setPassword("pwd");
        user.setUserName("test");
        user.setSalt("x");
        user.setLocked(2);
        int rtn = userService.insert(user);
        logger.info("test insert end "+rtn);
    }
    
    public static void testUpdate(){
    	logger.info("test update start");
    	UsersService userService  = ctx.getBean(UsersService.class);
        Users user = new Users();
        user.setId(10L);
        user.setPassword("pwd2");
        user.setUserName("test");
        user.setSalt("x2");
        user.setLocked(1);
        int rtn = userService.update(user);
        logger.info("test update end "+rtn);
    }
    public static void testDelete(){
    	logger.info("test delete start");
    	UsersService userService  = ctx.getBean(UsersService.class);
        int rtn = userService.delete(10L);
        logger.info("test delete end "+rtn);
    }
}
