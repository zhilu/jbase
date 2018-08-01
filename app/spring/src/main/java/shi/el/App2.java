package shi.el;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App2 {

	public static void main(String[] args) {
	
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
			Operators op = (Operators) context.getBean("operatorsBean");
			System.out.println(op.toString());
			TernaryOperator tern = (TernaryOperator) context.getBean("ternaryOperatorBean");
			System.out.println(tern.toString());
			context.close();
	}
}