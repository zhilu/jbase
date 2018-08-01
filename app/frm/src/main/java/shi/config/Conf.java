package shi.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shi.util.TypeUtil;

/**
 * 参数的统一加载
 * 
 * @author shi
 *
 */
public class Conf {

	static {
		// PropertyConfigurator.configure(Constant.LOG_CONF);
		DOMConfigurator.configure(Constant.LOG_CONF);
	}
	private static final Logger logger = LoggerFactory.getLogger(Conf.class);

	public static int isOK = 0;

	public void init() {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(Constant.SYSTEM_CONF);
			prop.load(in);
		} catch (IOException e) {
			logger.error("load system conf error", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				logger.error("close error", e);
			}
		}

		isOK = TypeUtil.toInt(prop.getProperty("is"), 0);
		logger.info("here is read from system file {}", isOK);
	}

}
