package org.codes;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.AbstractContext;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

public class Main {

	public static void main(String[] args) throws Exception {
		TemplateEngine templateEngine = new TemplateEngine();
		FileTemplateResolver  templateResolver = new FileTemplateResolver();
		templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setPrefix("./template/");
        templateResolver.setSuffix(".tpl");
        templateEngine.setTemplateResolver(templateResolver);
        
        AbstractContext context = new Context();
        FileWriter fw = new FileWriter(new File("test.txt"));
        List<String> items = new ArrayList<String>();
        items.add("first");
        items.add("two");
        items.add("third");
        context.setVariable("items", items);
        context.setVariable("num", 5);
        templateEngine.process("test", context, fw);
        System.out.println("over");
	}
}
