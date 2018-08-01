package shi.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("testMapListBean")
public class TestMapList {
	
	@Value("#{testBean.map['key1']}")
	private String mapElement;
 
	@Value("#{testBean.list[0]}")
	private String listElement;
  
	public String getMapElement() {
		return mapElement;
	}

	public void setMapElement(String mapElement) {
		this.mapElement = mapElement;
	}

	public String getListElement() {
		return listElement;
	}

	public void setListElement(String listElement) {
		this.listElement = listElement;
	}

	@Override
	public String toString() {
		return "TestMapList [mapElement=" + mapElement + ", listElement=" + listElement + "]";
	}
	
}
