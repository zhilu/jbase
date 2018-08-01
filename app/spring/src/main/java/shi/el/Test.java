package shi.el;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("testBean")
public class Test {

	private Map<String, String> map;
	private List<String> list;
	private String email;
 
	public Test() {
		map = new HashMap<String, String>();
		map.put("key1", "Value 1");
		map.put("key2", "Value 2");
		map.put("key3", "Value 3");
 
		list = new ArrayList<String>();
		list.add("list0");
		list.add("list1");
		list.add("list2");
		
		email = "hello@javacodegeeks.com";
 
	}

	public Map<String, String> getMap() {
		return map;
	}
 
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
 
	public List<String> getList() {
		return list;
	}
 
	public void setList(List<String> list) {
		this.list = list;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}