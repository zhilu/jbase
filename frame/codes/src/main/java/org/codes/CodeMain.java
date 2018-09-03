package org.codes;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.AbstractContext;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.util.StringUtils;

public class CodeMain {
	private static final Logger logger = LoggerFactory.getLogger(CodeMain.class);
	
	public final static String JAVA_DIRECTORY = "src/main/java";
	public final static String RESOURCES_DIRECTORY = "src/main/resources";
	
	public final static String MAPPER_PROJECT_DIRECTORY="D:/workspace/frame/dal/";
	public final static String SERVICE_PROJECT_DIRECTORY="D:/workspace/frame/biz/";
	public final static String web_PROJECT_DIRECTORY="D:/workspace/frame/web/";
	public final static String service_package="org.biz";
	public final static String web_package="org.web";

	public final static String mapperTpl="mapper";
	public final static String xmlMapperTpl="mapperXml";
	public final static String domainTpl="domain";
	public final static String serviceTpl="service";
	public final static String serviceImplTpl="serviceImpl";
	public final static String controllerTpl="controller";
	
	public final static String classAuhtor="shi";
	private final static String pacakgeName="org.dal";


	private static String tableName="sys_users";
	

	public static IContext getContext(Config conf){
		AbstractContext context = new Context();
		context.setVariable("classAuthor", classAuhtor);
		context.setVariable("packageName", pacakgeName);
		context.setVariable("servicePackage", service_package);
		context.setVariable("webPackage", web_package);
		context.setVariable("table", conf.getTable());
		context.setVariable("columns", conf.getTable().getColumns());
		context.setVariable("instanceClassName", tableName2ClassName(conf.getTable().getTableName()));
		context.setVariable("ClassName", StringUtils.capitalize(tableName2ClassName(conf.getTable().getTableName())));
		return context;
	}
	
	private static Object tableName2ClassName(String tableName) {
		return DBUtil.underscore2Camelcase(tableName, "sys");
	}

	public static void main(String[] args) throws Exception {
		Config conf = new Config();
		conf.setMapperProject(MAPPER_PROJECT_DIRECTORY);
		conf.setServiceProject(SERVICE_PROJECT_DIRECTORY);
		conf.setControllerProject(web_PROJECT_DIRECTORY);
		conf.setServicePackage(service_package);
		conf.setControllerPackage(web_package);
		conf.setPackageName(pacakgeName);
		conf.setEnableMapper(true);
		conf.setMapperTemplate(mapperTpl);
		conf.setEnableDomain(true);
		conf.setDomainTemplate(domainTpl);
		conf.setEnableService(true);
		conf.setServiceTemplate(serviceTpl);
		conf.setServiceImplTemplate(serviceImplTpl);
		conf.setEnableXmlMapper(true);
		conf.setXmlMapperTemplate(xmlMapperTpl);
		conf.setEnableController(true);
		conf.setControllerTemplate(controllerTpl);
		
		
		TemplateEngine templateEngine = new TemplateEngine();
		FileTemplateResolver  templateResolver = new FileTemplateResolver();
		templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setPrefix("./template/");
        templateResolver.setSuffix(".tpl");
        templateEngine.setTemplateResolver(templateResolver);
        
        List<Table> tables = DBUtil.getTableList("shiro",tableName);
        for(Table table:tables){
        	conf.setTable(table);
        	generateCode(templateEngine,conf);
        }
	}

	private static void generateCode(TemplateEngine templateEngine, Config conf) throws Exception {
		IContext context = getContext(conf);
		String className = (String) context.getVariable("ClassName");
		if(conf.isEnableMapper() && conf.getMapperTemplate().length()>0){
        	String mapperDirectory = conf.getMapperProject()+JAVA_DIRECTORY+"/"+conf.getPackageName().replace(".", "/");
        	File file = new File(mapperDirectory+"/mapper/"+className+"Mapper.java");
        	if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                templateEngine.process(conf.getMapperTemplate(), context, fw);
                fw.close();
        	}else{
        		logger.info(file.getAbsolutePath()+"已存在");
        	}
        }
		if(conf.isEnableDomain() && conf.getDomainTemplate().length()>0){
        	String mapperDirectory = conf.getMapperProject()+JAVA_DIRECTORY+"/"+conf.getPackageName().replace(".", "/");
        	File file = new File(mapperDirectory+"/bo/"+className+".java");
        	if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                templateEngine.process(conf.getDomainTemplate(), context, fw);
                fw.close();
        	}else{
        		logger.info(file.getAbsolutePath()+"已存在");
        	}
        }
		if(conf.isEnableService() && conf.getServiceTemplate().length()>0 && conf.getServiceImplTemplate().length()>0){
        	String mapperDirectory = conf.getServiceProject()+JAVA_DIRECTORY+"/"+conf.getServicePackage().replace(".", "/");
        	File file = new File(mapperDirectory+"/service/"+className+"Service.java");
        	if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                templateEngine.process(conf.getServiceTemplate(), context, fw);
                fw.close();
        	}else{
        		logger.info(file.getAbsolutePath()+"已存在");
        	}
        	
            file = new File(mapperDirectory+"/service/impl/"+className+"ServiceImpl.java");
        	if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                templateEngine.process(conf.getServiceImplTemplate(), context, fw);
                fw.close();
        	}else{
        		logger.info(file.getAbsolutePath()+"已存在");
        	}
        }
		if(conf.isEnableController() && conf.getControllerTemplate().length()>0){
        	String mapperDirectory = conf.getControllerProject()+JAVA_DIRECTORY+"/"+conf.getControllerPackage().replace(".", "/");
        	File file = new File(mapperDirectory+"/controller/"+className+"Controller.java");
        	if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                templateEngine.process(conf.getControllerTemplate(), context, fw);
                fw.close();
        	}else{
        		logger.info(file.getAbsolutePath()+"已存在");
        	}
        }
		
		if(conf.isEnableXmlMapper() && conf.getXmlMapperTemplate().length()>0){
        	String mapperDirectory = conf.getMapperProject()+RESOURCES_DIRECTORY;
        	File file = new File(mapperDirectory+"/mapper/"+className+"Mapper.xml");
        	if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                templateEngine.process(conf.getXmlMapperTemplate(), context, fw);
                fw.close();
        	}else{
        		logger.info(file.getAbsolutePath()+"已存在");
        	}
        }
	}
}
