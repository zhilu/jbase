package ald.rc.commom.util;

import java.util.regex.Pattern;

public class ValidateUtil {
	public static Pattern PATTERN_CHINESE=Pattern.compile("[\\u4e00-\\u9fa5]{2,25}");
	public static Pattern PATTERN_EMAIL=Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	public static Pattern PATTERN_PHONE=Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
	public static Pattern PATTERN_USER_NAME=Pattern.compile("^(?![0-9]+$)[0-9A-Za-z\u0391-\uFFE5]{2,15}$");
	public static Pattern isIDCard1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
	public static Pattern isIDCard2 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
}
