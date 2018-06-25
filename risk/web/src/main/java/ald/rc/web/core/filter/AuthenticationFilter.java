package ald.rc.web.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AuthenticationFilter implements Filter {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("filter init");
		ServletContext context = config.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		logger.info("applicationContext--------" + ctx);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.toString();
		String path = httpRequest.getServletPath();
		String tag = httpRequest.getParameter("tag");

		logger.info("t1--------");
		logger.info("t2" + path);
		logger.info("t3" + tag);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
