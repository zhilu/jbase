package shi.util;

public class SQLUtils {

	private SQLUtils(){}
	
	/**
	 * 用''包装一下原字符串,便于组SQL语句
	 * @param str
	 * @return
	 */
	public static String SQLString(String str){
		if(null != str){
			return "'"+str+"'";
		}else{
			return null;
		}
	}
}
