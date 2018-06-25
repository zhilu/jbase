package ald.rc.codehelper.orm;

public class OrmConfig {

	private String database = null;
	private String tableName = null;
	private boolean domain = true;
	private String domainPackage = null;
	private boolean dao = true;
	private String daoPackage = null;
	private boolean service = true;
	private String servicePackage = null;
	private boolean mapper = true;
	private String mapperPackage = null;

	private String projectPath = null;

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isDomain() {
		return domain;
	}

	public void setDomain(boolean domain) {
		this.domain = domain;
	}

	public String getDomainPackage() {
		return domainPackage;
	}

	public void setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
	}

	public boolean isDao() {
		return dao;
	}

	public void setDao(boolean dao) {
		this.dao = dao;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public boolean isService() {
		return service;
	}

	public void setService(boolean service) {
		this.service = service;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public boolean isMapper() {
		return mapper;
	}

	public void setMapper(boolean mapper) {
		this.mapper = mapper;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	
}
