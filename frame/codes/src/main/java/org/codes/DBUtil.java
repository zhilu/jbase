package org.codes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.util.StringUtils;

public class DBUtil {

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(Config.DB_DATABASE_URL, Config.DB_MYSQL_USER,
					Config.DB_MYSQL_PASSWORD);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Table> getTableList(String dbName,String tableName) {
		List<Table> tables = new ArrayList<Table>();
		Connection conn = getConn();
		Statement stmt = null;
		try {
			String sql = "SELECT table_name,table_comment FROM information_schema.TABLES  WHERE table_schema='"
					+ dbName + "'";
			if(!StringUtils.isEmptyOrWhitespace(tableName)){
				sql = sql + " AND table_name='" + tableName+"'";
			}
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Table table = new Table();
				table.setTableName(rs.getString("table_name"));
				table.setTableComment(rs.getString("table_comment"));
				List<Column> columns = getTableColumn(dbName, table.getTableName());
				table.setColumns(columns);
				tables.add(table);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}
		return tables;
	}

	public static List<Column> getTableColumn(String database, String tableName) {
		List<Column> columns = new ArrayList<Column>();
		Connection conn = getConn();
		Statement stmt = null;
		try {
			String sql = "SELECT COLUMN_NAME,DATA_TYPE,COLUMN_KEY,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='"
					+ database + "' AND TABLE_NAME='" + tableName + "'";
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Column column = new Column();
				String columnName = rs.getString("COLUMN_NAME");
				column.setOrginColumnName(columnName);
				column.setColumnName(underscore2Camelcase(columnName,""));;
				String sqlDataType = rs.getString("DATA_TYPE");
				column.setJavaDataType(sqlTypeToJavaType(sqlDataType));
				column.setJdbcDataType(sqlTypeToJdbcType(sqlDataType));
				column.setColumnComment(rs.getString("COLUMN_COMMENT"));
				columns.add(column);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}
		return columns;
	}

	public static String underscore2Camelcase(String str, String ignore) {
		String name = null;
		String[] split = StringUtils.split(str, "_");
		for (String string : split) {
			if (name == null) {
				if (string.equals(ignore)) {
					continue;
				}
				name = string;
			} else
				name += StringUtils.capitalize(string);
		}
		return name;
	}

	/**
	 * 功能：获得列的数据类型
	 *
	 * @param sqlType
	 * @return
	 */
	public static String sqlTypeToJavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			return "Boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney") || sqlType.equalsIgnoreCase("double")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}
		return "String";
	}

	/**
	 * 功能：获得列的数据类型
	 *
	 * @param sqlType
	 * @return
	 */
	public static String sqlTypeToJdbcType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			return "BIT";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "INTEGER";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "INTEGER";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "INTEGER";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "INTEGER";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "DECIMAL";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney") || sqlType.equalsIgnoreCase("double")) {
			return "DECIMAL";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "VARCHAR";
		} else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")
				|| sqlType.equalsIgnoreCase("timestamp")) {
			return "TIMESTAMP";
		}

		return "VARCHAR";
	}

	public static void close(Statement stmt, Connection conn) {
		try {
			if (null != stmt) {
				stmt.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
