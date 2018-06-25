package ald.rc.api.core;

import java.util.List;

/**
 * Scenario and related rules 
 * @author shiguihong
 */
public interface Scenario{

	/**
	 * 场景名称
	 * @return
	 */
	String getScenarioName();
	/**
	 * 加载场景关联的规则
	 */
	List<Rule> loadRuleChain();
	/**
	 * 校验规则
	 */
	void evaluateRule();
	/**
	 * 是否进行子场景
	 */
	boolean needStop();
	/**
	 * 获取场景的执行结果
	 */
	void saveResult();
}
