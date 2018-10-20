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
 *  1. У��ʵ��ʱ�����Բ����á���������Խ��뷽�����������׳��쳣
 *  2. У�����ʱ��BindingResult������Ч
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
	 *  ������ValidateĬ�ϲ���Ч����Ҫ����
	 *  1. ���ô�������MethodValidationPostProcessor
	 *  2 ������ @Validatedע��
	 */
    @RequestMapping("/string")  // ��� @NotEmpty ����Ч��
    public String valiateString(@NotEmpty(message = "str����Ϊ��") String str){
        System.out.println(str);
        return "index";
    }
}
