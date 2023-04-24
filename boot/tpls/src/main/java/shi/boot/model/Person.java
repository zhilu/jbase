package shi.boot.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {
	
	@NotEmpty
	private String name;
	@Min(value = 18)
	private Integer age;
	
	public Person() {
		super();
	}
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
