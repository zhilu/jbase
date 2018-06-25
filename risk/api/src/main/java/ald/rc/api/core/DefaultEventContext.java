package ald.rc.api.core;

import java.util.Map;

public class DefaultEventContext<R> implements EventContext<R>{

	private Event event;
	private Map<String,Object> eventData;
	
	private ScenarioManager<Scenario> scenarioManager;
	public DefaultEventContext(Event event,Map<String,Object> eventData) {
		this.event=event;
		this.eventData =eventData;
	}
	
	@Override
	public void generateScenarioManager() {
		scenarioManager = new DefaultScenarioManager(event, eventData);
	}
	
	@Override
	public void loadData() {
		scenarioManager.getScenarios();
		
	}

	@Override
	public boolean isFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public R getEventResult() {
		// TODO Auto-generated method stub
		return null;
	}




}
