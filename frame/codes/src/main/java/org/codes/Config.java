package org.codes;

public class Config {
		
	public static String DB_MYSQL_USER = "root";
	public static String DB_MYSQL_PASSWORD = "";
	public static String DB_DATABASE_NAME = "shiro";
	public static String DB_DATABASE_URL = "jdbc:mysql://localhost:3306/" + DB_DATABASE_NAME+ "?characterEncoding=utf8&useSSL=false";
	
	
	private String serviceProject;
	private String controllerProject;
	private String mapperProject;
	private String packageName; 
	private String servicePackage;
	private String controllerPackage;
	
	private String classAuthor;
	private String className;
	
	private String xmlMapperTemplate;
	private String mapperTemplate;
	private String domainTemplate;
	private String serviceTemplate;
	private String serviceImplTemplate;
	private String controllerTemplate;
	
	private boolean enableMapper;
	private boolean enableXmlMapper;
	private boolean enableDomain;
	private boolean enableService;
	private boolean enableController;
	
	private Table table;
	
	
	public String getServiceProject() {
		return serviceProject;
	}
	public void setServiceProject(String serviceProject) {
		this.serviceProject = serviceProject;
	}
	public String getControllerProject() {
		return controllerProject;
	}
	public void setControllerProject(String controllerProject) {
		this.controllerProject = controllerProject;
	}
	public String getMapperProject() {
		return mapperProject;
	}
	public void setMapperProject(String mapperProject) {
		this.mapperProject = mapperProject;
	}
	public String getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
	public String getControllerPackage() {
		return controllerPackage;
	}
	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}
	public String getClassAuthor() {
		return classAuthor;
	}
	public void setClassAuthor(String classAuthor) {
		this.classAuthor = classAuthor;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getXmlMapperTemplate() {
		return xmlMapperTemplate;
	}
	public void setXmlMapperTemplate(String xmlMapperTemplate) {
		this.xmlMapperTemplate = xmlMapperTemplate;
	}
	public String getMapperTemplate() {
		return mapperTemplate;
	}
	public void setMapperTemplate(String mapperTemplate) {
		this.mapperTemplate = mapperTemplate;
	}
	public String getDomainTemplate() {
		return domainTemplate;
	}
	public void setDomainTemplate(String domainTemplate) {
		this.domainTemplate = domainTemplate;
	}
	public String getServiceTemplate() {
		return serviceTemplate;
	}
	public void setServiceTemplate(String serviceTemplate) {
		this.serviceTemplate = serviceTemplate;
	}
	public String getServiceImplTemplate() {
		return serviceImplTemplate;
	}
	public void setServiceImplTemplate(String serviceImplTemplate) {
		this.serviceImplTemplate = serviceImplTemplate;
	}
	public String getControllerTemplate() {
		return controllerTemplate;
	}
	public void setControllerTemplate(String controllerTemplate) {
		this.controllerTemplate = controllerTemplate;
	}
	public boolean isEnableMapper() {
		return enableMapper;
	}
	public void setEnableMapper(boolean enableMapper) {
		this.enableMapper = enableMapper;
	}
	public boolean isEnableXmlMapper() {
		return enableXmlMapper;
	}
	public void setEnableXmlMapper(boolean enableXmlMapper) {
		this.enableXmlMapper = enableXmlMapper;
	}
	public boolean isEnableDomain() {
		return enableDomain;
	}
	public void setEnableDomain(boolean enableDomain) {
		this.enableDomain = enableDomain;
	}
	public boolean isEnableService() {
		return enableService;
	}
	public void setEnableService(boolean enableService) {
		this.enableService = enableService;
	}
	public boolean isEnableController() {
		return enableController;
	}
	public void setEnableController(boolean enableController) {
		this.enableController = enableController;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	
	
	
}
