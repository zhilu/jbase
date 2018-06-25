package ald.rc.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;

import ald.rc.api.bo.LoginModel;
import ald.rc.api.bo.LoginResult;
import ald.rc.api.core.EventBaseController;
import ald.rc.api.core.EventContext;

@Controller
@Scope(value="prototype")
public class LoginController extends EventBaseController <LoginModel,LoginResult> {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Override
	protected EventContext<LoginResult> getEventContext(LoginModel m) {
		
		return null;
	}
	
	@RequestMapping(value = "/test")
	public void indexTest() {
		logger.info("{}yy","xx");
		Map<String,Object> res = Maps.newHashMap();
		res.put("nnnn", "yyyyy");
		writeToResponse(res);
	}
	
	@RequestMapping(value=ApiPath.LOGIN_ASY)
	public void loginAsy(LoginModel model){
		
	}
	
	@RequestMapping(value=ApiPath.LOGIN_SYN)
	public void loginSyn(LoginModel model){
		
	}

}
