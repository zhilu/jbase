package shi.util;

public class TypeUtil {
	
	public static int toInt(Object obj,int defaultValue){
		try {
			String val=obj.toString();
			return Integer.parseInt(val);
		} catch (Exception e) {
			return defaultValue;
		}
	}
}
