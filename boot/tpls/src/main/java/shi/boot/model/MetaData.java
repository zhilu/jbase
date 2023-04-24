package shi.boot.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaData {
    @JsonProperty("data")
    private Map<String, String> map  = new HashMap<>();

    public MetaData(Map<String, String> map) {
        this.map = map;
    }

    public String get(String key) {
        return map.get(key);
    }

	public Map<String, String> getMap() {
		return map;
	}
    
    
}
