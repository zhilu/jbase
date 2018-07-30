package org.dal;

import org.dal.bo.Users;
import org.dal.mapper.UsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger logger =LoggerFactory.getLogger(Main.class);
    
    public static ClassPathXmlApplicationContext ctx = null;
    static{
    	ctx = new ClassPathXmlApplicationContext("config/spring/dal-bean.xml");
    }

    public static void main(String[] args) {
    	testInsert();
    	testUpdate();
    	testDelete();
        ctx.close();
    }
    
    public static void testInsert(){
    	logger.info("test insert start");
    	UsersMapper mapper  = ctx.getBean(UsersMapper.class);
        Users user = new Users();
        user.setId(10L);
        user.setPassword("pwd");
        user.setUserName("test");
        user.setSalt("x");
        user.setLocked(2);
        int rtn = mapper.insert(user);
        logger.info("test insert end "+rtn);
    }
    
    public static void testUpdate(){
    	logger.info("test insert start");
    	UsersMapper mapper  = ctx.getBean(UsersMapper.class);
        Users user = new Users();
        user.setId(10L);
        user.setPassword("pwd2");
        user.setUserName("test");
        user.setSalt("x2");
        user.setLocked(1);
        int rtn = mapper.update(user);
        logger.info("test insert end "+rtn);
    }
    public static void testDelete(){
    	logger.info("test insert start");
    	UsersMapper mapper  = ctx.getBean(UsersMapper.class);
        int rtn = mapper.delete(10L);
        logger.info("test insert end "+rtn);
    }
}
