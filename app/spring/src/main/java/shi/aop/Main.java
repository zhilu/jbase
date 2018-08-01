package shi.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("shi.aop");
		BusinessTarget target = context.getBean(BusinessTarget.class);
		target.sayHello();
		target.performTransaction("JavaCodeGeeks");
		target.merryGoAround();
		target.throwException();
		context.close();
	
	}
}
