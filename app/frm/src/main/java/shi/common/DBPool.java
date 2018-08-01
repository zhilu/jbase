package shi.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shi.config.Constant;

public class DBPool {

	private static final Logger logger = LoggerFactory.getLogger(DBPool.class);
	static {
		try {
			JAXPConfigurator.configure(Constant.DB_CONF, false);
		} catch (ProxoolException e) {
			logger.error("database conf error", e);
		}
	}

	/**
	 * 从默认连接池取得可用连接
	 * 
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("proxool.default");
		} catch (SQLException e) {
			logger.error("open database connection error", e);
		}
		return conn;
	}

	public static void close(Connection conn, Statement stmt) {
		try {
			if (null != stmt) {
				stmt.close();
			}
			if (null != conn){
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("close database connection error", e);
		}
	}

}
