package ald.rc.codehelper.orm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Util {

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(OrmHelper.DB_DATABASE_URL, OrmHelper.DB_MYSQL_USER, OrmHelper.DB_MYSQL_PASSWORD);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Table> getTableList(String dbName) {
		List<Table> tables = new ArrayList<Table>();
		Connection conn = getConn();
		Statement stmt = null;
		try {
			String sql = "SELECT table_name,table_comment FROM information_schema.TABLES  WHERE table_schema='" + dbName
					+ "'";
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Table table = new Table();
				table.setDatabase(dbName);
				table.setTableName(rs.getString("table_name"));
				table.setTableComment(rs.getString("table_comment"));
				List<Column> columns = getList(dbName, table.getTableName());
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

	public static List<Column> getList(String database, String tableName) {
		List<Column> columns = new ArrayList<Column>();
		Connection conn = getConn();
		Statement stmt = null;
		try {
			String sql = "SELECT COLUMN_NAME,DATA_TYPE,COLUMN_KEY,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='"
					+ database + "' AND TABLE_NAME='" + tableName + "'";
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Column column = new UsedColumn();
				String columnName = rs.getString("COLUMN_NAME");
				column.setColumnName(columnName);
				column.setDataType(rs.getString("DATA_TYPE"));
				column.setColumnKey(rs.getString("COLUMN_KEY"));
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
			}
			else
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

	/**
	 * 将内容写入文件
	 *
	 * @param content
	 * @param filePath
	 */
	public static void writeFile(String content, String filePath) {
		try {
			if (createFile(filePath)) {
				FileWriter fileWriter = new FileWriter(filePath, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(content);
				bufferedWriter.close();
				fileWriter.close();
			} else {
				System.err.println("生成失败，文件已存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建单个文件
	 *
	 * @param descFileName
	 *            文件名，包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			System.err.println("文件 " + descFileName + " 已存在!");
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			System.err.println(descFileName + " 为目录，不能创建目录!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				System.err.println("创建文件所在的目录失败!");
				return false;
			}
		}

		// 创建文件
		try {
			if (file.createNewFile()) {
				System.err.println(descFileName + " 文件创建成功!");
				return true;
			} else {
				System.err.println(descFileName + " 文件创建失败!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(descFileName + " 文件创建失败!");
			return false;
		}
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
