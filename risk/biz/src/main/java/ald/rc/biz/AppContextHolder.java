package ald.rc.biz;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContextHolder implements ApplicationContextAware {
	private static ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		appContext = ctx;
	}
	public static boolean isReady() {
		return appContext != null;
	}

	public static ApplicationContext get() {
		return appContext;
	}

	public static Object getBean(String beanName) {
		return appContext.getBean(beanName);
	}

	public static boolean containsBean(String beanName) {
		return appContext.containsBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> type) {
		return appContext.getBean(beanName, type);
	}
}