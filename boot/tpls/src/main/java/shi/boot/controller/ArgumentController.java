package shi.boot.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shi.boot.model.MetaData;

@RestController
public class ArgumentController {

	@RequestMapping(path = "/args")
	public Map<String, String> create(MetaData metaData) {
		System.out.printf(metaData.toString());
		return metaData.getMap();
	}

}
