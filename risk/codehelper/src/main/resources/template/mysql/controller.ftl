package ${packageName}.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ${packageName}.web.controller.BaseController;
import ${packageName}.service.${ClassName}Service;

 /**
 * ${functionName}Controller
 * 
 * @author ${classAuthor}
 * @version 1.0.0
 * @date ${classDate}
 */
@Controller
public class ${ClassName}Controller extends BaseController {

	@Resource
	private ${ClassName}Service ${className}Service;

}
