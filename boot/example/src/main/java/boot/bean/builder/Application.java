package boot.bean.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import boot.bean.Foo;

@SpringBootApplication
@Import({ UseBuilderImportBeanDefinitionRegistrar.class })
public class Application implements CommandLineRunner {
	
	@Autowired
	private Foo foo;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        foo.foo();
    }

}
