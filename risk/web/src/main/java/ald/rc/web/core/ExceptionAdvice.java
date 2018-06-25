package ald.rc.web.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

	private static final Logger logger = LogManager.getLogger();

	@ExceptionHandler({ ArithmeticException.class })
	public String handleArithmeticException(Exception e) {
		logger.error("ArithmeticException", e);
		return "error";
	}

	@ExceptionHandler({Exception.class })
	public String handlerException(Exception e) {
		logger.error("Exception", e.getMessage());
		return "error";
	}
}
