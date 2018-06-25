package ald.rc.web.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigListener implements ServletContextListener {
	
	private static final Logger logger = LogManager.getLogger();
	@Override
    public void contextInitialized(ServletContextEvent sce) {
		logger.info("lister start");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	logger.info("lister end");
    }
}
