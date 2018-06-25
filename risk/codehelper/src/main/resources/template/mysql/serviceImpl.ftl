package ${packageName}.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ${packageName}.mapper.BaseMapper;
import ${packageName}.service.impl.BaseServiceImpl;
import ${packageName}.mapper.${ClassName}Mapper;
import ${packageName}.domain.${ClassName};
import ${packageName}.service.${ClassName}Service;


/**
 * ${className}ServiceImpl
 * 
 * @author ${classAuthor}
 * @date ${classDate}
 */
 
@Service("${className}Service")
public class ${ClassName}ServiceImpl extends BaseServiceImpl<${ClassName}, Long> implements ${ClassName}Service {
	
    private static final Logger logger = LoggerFactory.getLogger(${ClassName}ServiceImpl.class);
   
    @Resource
    private ${ClassName}Mapper ${className}Mapper;

	@Override
	public BaseMapper<${ClassName}, Long> getMapper() {
		return ${className}Mapper;
	}
	
}