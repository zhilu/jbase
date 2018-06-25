package ald.rc.web.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 计算每次请求耗时
 * @author solverpeng
 * @create 2016-09-01-17:46
 */
public class ConsumeTimeInterceptor extends HandlerInterceptorAdapter{
    
	private static final Logger logger = LogManager.getLogger();
	
	private NamedThreadLocal<Long> consumeTime = new NamedThreadLocal<>("consume_time");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        consumeTime.set(System.currentTimeMillis());
        logger.info("ConsumeTimeInterceptor#preHandle");
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	logger.info("ConsumeTimeInterceptor#afterCompletion");
        Long threadEnd = System.currentTimeMillis();
        Long threadStart = consumeTime.get();
        Long consume = threadEnd - threadStart;
        if(consume > 10) {
        	logger.info("add to log: " + consume);
        }
    }
}
