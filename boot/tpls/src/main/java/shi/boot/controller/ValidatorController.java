package shi.boot.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shi.boot.model.Person;


/**
 *  BindingResult 
 *  1. 校验实体时，可以不配置。配置则可以进入方法，不配置抛出异常
 *  2. 校验参数时，BindingResult配置无效
 *  
 * @author mm
 *
 */
@RestController
@Validated
public class ValidatorController {

	@RequestMapping("/testValidator.html")
	public String index(@Valid Person person,BindingResult result) {
		System.out.println(person.getName() + person.getAge());
		System.out.println(result.toString());
		return "index";
	}
	
	/**
	 *  参数的Validate默认不生效，需要配置
	 *  1. 后置处理器：MethodValidationPostProcessor
	 *  2 类配置 @Validated注解
	 */
    @RequestMapping("/string")  // 这个 @NotEmpty 是生效的
    public String valiateString(@NotEmpty(message = "str不能为空") String str){
        System.out.println(str);
        return "index";
    }
}
