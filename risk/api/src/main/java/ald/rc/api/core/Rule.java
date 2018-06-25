package ald.rc.api.core;

public interface Rule {

    Integer getRuleId();
	int getRuleType();
	void evaluate();
}
