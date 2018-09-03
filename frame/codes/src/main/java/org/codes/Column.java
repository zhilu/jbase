package org.codes;

public class Column {

	private String columnName;
	private String orginColumnName;
	private String columnComment;
	private String javaDataType;
	private String jdbcDataType;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getOrginColumnName() {
		return orginColumnName;
	}
	public void setOrginColumnName(String orginColumnName) {
		this.orginColumnName = orginColumnName;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getJavaDataType() {
		return javaDataType;
	}
	public void setJavaDataType(String javaDataType) {
		this.javaDataType = javaDataType;
	}
	public String getJdbcDataType() {
		return jdbcDataType;
	}
	public void setJdbcDataType(String jdbcDataType) {
		this.jdbcDataType = jdbcDataType;
	}
	
}
