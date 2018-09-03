package [(${servicePackage})].service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import [(${packageName})].BaseMapper;
import [(${packageName})].mapper.[(${ClassName})]Mapper;
import [(${packageName})].bo.[(${ClassName})];
import [(${servicePackage})].AbstractBaseService;
import [(${servicePackage})].service.[(${ClassName})]Service;


/**
 * @author [(${classAuthor})]
 */
 
@Service("[(${#strings.unCapitalize(ClassName)})]Service")
public class [(${ClassName})]ServiceImpl extends AbstractBaseService<[(${ClassName})], Long> implements [(${ClassName})]Service {
	
    private static final Logger logger = LoggerFactory.getLogger([(${ClassName})]ServiceImpl.class);
   
    @Resource
    private [(${ClassName})]Mapper [(${#strings.unCapitalize(ClassName)})]Mapper;

	@Override
	public BaseMapper<[(${ClassName})], Long> getMapper() {
		return [(${#strings.unCapitalize(ClassName)})]Mapper;
	}
	
}