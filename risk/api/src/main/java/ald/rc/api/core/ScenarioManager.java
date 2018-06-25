package ald.rc.api.core;

import java.util.List;

/**
 * 事件生成的场景管理器
 * @author shiguihong
 */
public interface ScenarioManager <T>{

	List<T> getScenarios();
}
