package shi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConf {

	private static Logger logger =LoggerFactory.getLogger(TestConf.class);
	public static void main(String[] args) {
		new Conf();
		
		logger.info("ok");
		logger.debug("this {}", 1);
		logger.warn("this {}", "warn");
		logger.error("this {}", 2);
	}
}
