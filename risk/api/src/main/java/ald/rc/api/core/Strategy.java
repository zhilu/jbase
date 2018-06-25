package ald.rc.api.core;

import java.util.List;

public interface Strategy {

    String getStrategyName();
    Integer getStrategyMode();
    List<Rule> getAllRule();
    String getStrategyDecision();
}
