package shi.aop;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public AnnotationAwareAspectJAutoProxyCreator aspectJAwareAdvisorAutoProxyCreator(){
		return new AnnotationAwareAspectJAutoProxyCreator();
	}
}
