package ald.rc.api.core;

public interface EventContext <R>{
	void generateScenarioManager();
	void loadData();
	boolean isFinish();
	R getEventResult();
}
