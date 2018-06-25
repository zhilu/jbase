package ald.rc.codehelper.orm;

import freemarker.template.utility.StringUtil;

public class UsedColumn extends Column {

	public String getAccessColumnName(){
		String accessName = StringUtil.capitalize(Util.underscore2Camelcase(getColumnName(),OrmHelper.DB_TABLE_PREFIXX));
		return accessName;
	}
	public String getFieldName(){
		return super.getColumnName();
	}
	
	public String getJdbcType(){
		return Util.sqlTypeToJdbcType(super.getDataType());
	}
	
	@Override
	public String getColumnName(){
		return Util.underscore2Camelcase(super.getColumnName(), OrmHelper.DB_TABLE_PREFIXX);
	}
	
	@Override
	public String getDataType(){
		String javaDataType = Util.sqlTypeToJavaType(super.getDataType());
		return javaDataType;
		
	}
}
