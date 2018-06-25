package ald.rc.api.core;

import java.util.List;
import java.util.Map;

public class DefaultScenarioManager implements ScenarioManager<Scenario> {

	private Event event;
	private Map<String,Object> eventData;
	
	DefaultScenarioManager(Event event,Map<String,Object> eventdata){
		this.event=event;
		this.eventData=eventdata;
	}
	
	@Override
	public List<Scenario> getScenarios() {
		System.out.println(event);
		System.out.println(eventData);
		return null;
	}

}
