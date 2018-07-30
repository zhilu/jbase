package [(${webPackage})].controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import [(${webPackage})].BaseController;
import [(${servicePackage})].service.[(${ClassName})]Service;

 /**
 * @author [(${classAuthor})]
 */
@Controller
public class [(${ClassName})]Controller extends BaseController {

	@Resource
	private [(${ClassName})]Service [(${className})]Service;

}