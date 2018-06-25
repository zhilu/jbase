package ald.rc.api.core;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import ald.rc.commom.util.ServletUtil;

@Controller
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
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
	
	protected void writeToResponse(Map<? extends Object,Object> res){
		try {
			ServletUtil.writeToResponse(response, res);
		} catch (IOException e) {
			logger.error("response data {} exception",res,e);
		} 
	}
	
}
