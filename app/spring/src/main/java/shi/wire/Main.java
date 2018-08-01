package shi.wire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("autowire.xml");
    	Animal animal = context.getBean(Animal.class);
    	
        System.out.println(animal);
        ((AbstractApplicationContext) context).close();
	}
}
