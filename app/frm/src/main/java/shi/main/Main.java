package shi.main;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shi.common.DBPool;
import shi.config.Conf;

public class Main {
	private static final Logger logger =LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		Conf conf = new Conf();
		
		conf.init();
		
		
		Connection conn = DBPool.getConn();
		
		logger.error("this conn {} ", conn);
		
		
		
	}
}
