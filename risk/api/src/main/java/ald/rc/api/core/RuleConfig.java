package ald.rc.api.core;

/**
 * @author shiguihong
 * @version 1.0
 */

public interface RuleConfig {

    int TYPE_INT = 1;
    int TYPE_STRING = 2;
    int TYPE_DOUBLE = 3;
    int TYPE_DATE = 4;

    int getDataType();
    String getDataName();
    String getFormula();
    RuleResult getSatisfiedResult();
    Object getDataValue();
    RuleResult getDefaultResult();
}
