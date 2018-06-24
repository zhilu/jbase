package utiltools;

import java.text.NumberFormat;
public class TypeUtil {

	private TypeUtil() {
	}

	public static Double toDouble(Object value, Double onError) {
		if (null == value || "".equals(value.toString())) {
			return onError;
		} else {
			if (value instanceof Long) {
				return ((Long) value).doubleValue();
			} else if (value instanceof Integer) {
				return ((Integer) value).doubleValue();
			} else if (value instanceof Short) {
				return ((Short) value).doubleValue();
			} else if (value instanceof Byte) {
				return ((Byte) value).doubleValue();
			} else if (value instanceof Double) {
				return ((Double) value).doubleValue();
			} else {
				return Double.parseDouble(value.toString());
			}
		}
	}

	public static Double toDouble(Object value) {
		return toDouble(value, null);
	}

	public static Long toLong(Object value, Long onError) {
		if (null == value || "".equals(value.toString())) {
			return onError;
		} else {
			if (value instanceof Long) {
				return ((Long) value).longValue();
			} else if (value instanceof Integer) {
				return ((Integer) value).longValue();
			} else if (value instanceof Short) {
				return ((Short) value).longValue();
			} else if (value instanceof Byte) {
				return ((Byte) value).longValue();
			} else if (value instanceof Double) {
				return ((Double) value).longValue();
			} else {
				return Long.parseLong(value.toString());
			}
		}
	}

	public static Long toLong(Object value) {
		return toLong(value, null);
	}
	
	
	public static Integer toInt(Object value, Integer onError) {
		if (null == value || "".equals(value.toString())) {
			return onError;
		} else {
			if (value instanceof Long) {
				return ((Long) value).intValue();
			} else if (value instanceof Integer) {
				return ((Integer) value).intValue();
			} else if (value instanceof Short) {
				return ((Short) value).intValue();
			} else if (value instanceof Byte) {
				return ((Byte) value).intValue();
			} else if (value instanceof Double) {
				return ((Double) value).intValue();
			} else {
				return Integer.parseInt(value.toString());
			}
		}
	}

	public static Integer toInt(Object value) {
		return toInt(value, null);
	}

	
	public static Short toShort(Object value, Short onError) {
		if (null == value || "".equals(value.toString())) {
			return onError;
		} else {
			if (value instanceof Long) {
				return ((Long) value).shortValue();
			} else if (value instanceof Integer) {
				return ((Integer) value).shortValue();
			} else if (value instanceof Short) {
				return ((Short) value).shortValue();
			} else if (value instanceof Byte) {
				return ((Byte) value).shortValue();
			} else if (value instanceof Double) {
				return ((Double) value).shortValue();
			} else {
				return Short.parseShort(value.toString());
			}
		}
	}

	public static Short toShort(Object value) {
		return toShort(value, null);
	}
	
	public static Byte toByte(Object value, Byte onError) {
		if (null == value || "".equals(value.toString())) {
			return onError;
		} else {
			if (value instanceof Long) {
				return ((Long) value).byteValue();
			} else if (value instanceof Integer) {
				return ((Integer) value).byteValue();
			} else if (value instanceof Short) {
				return ((Short) value).byteValue();
			} else if (value instanceof Byte) {
				return ((Byte) value).byteValue();
			} else if (value instanceof Double) {
				return ((Double) value).byteValue();
			} else {
				return Byte.parseByte(value.toString());
			}
		}
	}

	public static Byte toByte(Object value) {
		return toByte(value, null);
	}
	
	public static String toNumber(double value,int decimal){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(decimal);
		return nf.format(value);
	}
}
