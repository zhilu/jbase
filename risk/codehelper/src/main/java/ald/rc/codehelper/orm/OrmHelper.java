package ald.rc.codehelper.orm;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrmHelper {
	public static String DB_MYSQL_USER = "user";
	public static String DB_MYSQL_PASSWORD = "123456";
	public static String DB_DATABASE_NAME = "risk";
	public static String DB_DATABASE_URL = "jdbc:mysql://localhost:3306/" + DB_DATABASE_NAME
			+ "?useUnicode=true&characterEncoding=utf8&useSSL=false";
	public static String DB_TABLE_PREFIXX = "arc";

	public static String templatePath = "D:/workspace/risk/codehelper/src/main/resources/template/mysql";
	public static String TEMPLATA_MAPPER_FILE = "mapper.ftl";
	public static String TEMPLATE_DO_FILE = "domain.ftl";
	public static String TEMPLATE_MAPPER_XML_FILE = "dbmapper.ftl";
	
	public static String auther = "shi";
	public static String tableName = null;

	public static String TARGET_PROJECT_PATH = "D:/workspace/risk/dal/";

	public static String SRC_PATH = "src/main/java/";
	public static String RESOURCES_PATH = "src/main/resources/";


	public static String packageNamePath = "ald/rc/dal";
	public static String mapperPath = "config/mappers/";

	public static String baseMapperPackage = "ald.rc.dal";
	public static String domainPackage = "ald.rc.dal.bo";
	public static String daoPacakge = "ald.rc.dal.mapper";

	public static void main(String[] args) throws Exception {
		OrmConfig config = new OrmConfig();
		config.setDatabase(OrmHelper.DB_DATABASE_NAME);
		config.setTableName(OrmHelper.tableName);
		config.setDomainPackage(domainPackage);
		config.setDaoPackage(daoPacakge);
		config.setMapperPackage(mapperPath);

		Map<String, Object> model = null;
		List<Table> tables = Util.getTableList(config.getDatabase());
		for (Table table : tables) {
			if (null != config.getTableName() && !table.getTableName().equals(config.getTableName())) {
				continue;
			}
			model = dataModel(table);
			generateFile(model, config);
		}
	}

	public static Map<String, Object> dataModel(Table table) {
		String camelcaseTableName = Util.underscore2Camelcase(table.getTableName(), OrmHelper.DB_TABLE_PREFIXX);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("packageName", OrmHelper.baseMapperPackage);
		model.put("tableComment", table.getTableComment());
		model.put("className", StringUtils.capitalize(camelcaseTableName));
		model.put("classAuthor", OrmHelper.auther);
		model.put("classDate", LocalDate.now().toString());
		model.put("tableName", table.getTableName());
		model.put("list", table.getColumns());
		model.put("listSize", table.getColumns().size());
		model.put("leftBraces", "{");
		model.put("rightBraces", "}");
		return model;
	}

	private static void generateFile(Map<String, Object> model, OrmConfig config) throws Exception {
		Configuration cfg = new Configuration(Configuration.getVersion());
		cfg.setDirectoryForTemplateLoading(new File(OrmHelper.templatePath));
		
		if (config.isDomain() && OrmHelper.TEMPLATE_DO_FILE != null) {
			Template beanTemplate = cfg.getTemplate(OrmHelper.TEMPLATE_DO_FILE);
			String beanContent = renderTemplate(beanTemplate, model);
			String className = model.get("className") + ".java";
			String beanFile = OrmHelper.TARGET_PROJECT_PATH + OrmHelper.SRC_PATH
					+ packageName2Path(config.getDomainPackage()) + File.separator + className;
			Util.writeFile(beanContent, beanFile);
		}
		
		if (config.isDao()  && null != OrmHelper.TEMPLATA_MAPPER_FILE) {
			Template daoTemplate = cfg.getTemplate(OrmHelper.TEMPLATA_MAPPER_FILE);
			String daoContent = renderTemplate(daoTemplate, model);
			String daoFile = OrmHelper.TARGET_PROJECT_PATH + OrmHelper.SRC_PATH +packageName2Path(config.getDaoPackage())+File.separator
					+ model.get("className") + "Mapper.java";
			Util.writeFile(daoContent, daoFile);
		}
		
		if(config.isMapper() && null != OrmHelper.TEMPLATE_MAPPER_XML_FILE){
			Template ibatisTemplate = cfg.getTemplate(OrmHelper.TEMPLATE_MAPPER_XML_FILE);
			String ibatisContent = OrmHelper.renderTemplate(ibatisTemplate, model);
			String batisSql = OrmHelper.TARGET_PROJECT_PATH + OrmHelper.RESOURCES_PATH +packageName2Path(config.getMapperPackage()) +model.get("className") + "Mapper.xml";
			Util.writeFile(ibatisContent, batisSql);
		}
		
		if(config.isService() && null != OrmHelper.TEMPLATA_MAPPER_FILE){
			//add template
		}

		
	}
	
	public static String renderTemplate(Template template, Object model) throws TemplateException, IOException {
		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}
	
	public static String packageName2Path(String packageName){
		return packageName.replace(".", "/");
	}
}
