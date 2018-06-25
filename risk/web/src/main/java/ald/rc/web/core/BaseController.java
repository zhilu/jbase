package ald.rc.web.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public abstract class BaseController {

	private static final Logger logger = LogManager.getLogger();

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		logger.info("baseContronller set req and resp");
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	@InitBinder
	protected final void initBinderInternal(WebDataBinder binder) {
		initBinder(binder);
	}

	protected void initBinder(WebDataBinder binder) {
	}

	@ExceptionHandler(value = {ArithmeticException.class })
	public String handleArithmeticException(Exception e) {
		logger.error("spring test class",e);
		return "error";
	}

}
