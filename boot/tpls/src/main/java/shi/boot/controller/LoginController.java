package shi.boot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String home() {
		return "login";
	}
	
	@RequestMapping("/index.html")
	public String index(String userName,String password) {
		System.out.println(userName+password);
		return "index";
	}
	
	@RequestMapping("/blank")
	public String blank(String userName,String password) {
		System.out.println(userName+password);
		return "blank";
	}
	
	
}
