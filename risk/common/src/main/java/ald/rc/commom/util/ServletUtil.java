package ald.rc.commom.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;

public class ServletUtil {
	
	public static final String UTF8="utf-8";
	
	public static void writeToResponse(HttpServletResponse response, Map<? extends Object, Object> res) throws UnsupportedEncodingException, IOException {
		response.setContentType(String.valueOf(ContentType.APPLICATION_JSON));
		OutputStreamWriter out = null;
		out = new OutputStreamWriter(response.getOutputStream(), Charset.forName(UTF8));
		JsonUtil.write(out, res);
	}
}
