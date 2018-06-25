package ald.rc.api.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ald.rc.commom.util.ServletUtil;


@ControllerAdvice
public class EventExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(EventExceptionHandler.class);
	public static final String CODE="code";
	public static final String MSG="msg";
	
	@ExceptionHandler({RuntimeException.class})
    public void runtimeExcptionDispose(RuntimeException e, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(CODE, 400);
        res.put(MSG, "系统异常");
        try {
        	logger.error("exception",e);
			ServletUtil.writeToResponse(response, res);
		} catch (IOException ex) {
			logger.error("response data {} exception",res,ex);
		} 
    }
}
