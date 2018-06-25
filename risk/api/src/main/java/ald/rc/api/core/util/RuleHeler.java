package ald.rc.api.core.util;

import ald.rc.api.core.IntOperator;
import ald.rc.api.core.Rule;
import ald.rc.api.core.RuleConfig;
import ald.rc.api.core.RuleResult;
import ald.rc.api.core.StringOperator;
import ald.rc.commom.util.TypeUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shiguihong
 * @version 1.0
 */

public abstract class RuleHeler {

    public static RuleResult appraiseSimpleRule(Rule rule, Map<String,Object> data){
        List<RuleConfig> ruleConfigs = CacheUtil.getRuleConfigs(rule.getRuleId());
        Set<RuleResult> ruleResults = new HashSet<>();
        for (RuleConfig ruleConfig : ruleConfigs) {
            int dataType = ruleConfig.getDataType();
            Object realValue = data.get(ruleConfig.getDataName());
            Object dataValue = ruleConfig.getDataValue();
            switch(dataType){
                case 1:
                    RuleResult ruleResult = simpleIntRule(realValue,dataValue,ruleConfig.getFormula(),ruleConfig.getSatisfiedResult(),ruleConfig.getDefaultResult());
                    ruleResults.add(ruleResult);
                    break;
                case 2:
                    ruleResult = simpleIntRule(realValue,dataValue,ruleConfig.getFormula(),ruleConfig.getSatisfiedResult(),ruleConfig.getDefaultResult());
                    ruleResults.add(ruleResult);
                    break;
                case 3:
                    ruleResult = simpleStringRule(realValue,dataValue,ruleConfig.getFormula(),ruleConfig.getSatisfiedResult(),ruleConfig.getDefaultResult());
                    ruleResults.add(ruleResult);
                    break;
                case 4:
                    ruleResult = simpleStringRule(realValue,dataValue,ruleConfig.getFormula(),ruleConfig.getSatisfiedResult(),ruleConfig.getDefaultResult());
                    ruleResults.add(ruleResult);
                    break;
                default :
                    // todo log
                    break;
            }

        }
        return finalResult(ruleResults);
    }

    private static RuleResult simpleStringRule(Object realValue, Object dataValue, String formula, RuleResult satisfiedResult, RuleResult defaultResult) {
        boolean typeMatch = checkDataType(realValue,dataValue,3);
        if(typeMatch){
            String realValueString = String.valueOf(realValue);
            String dataValueString = String.valueOf(dataValue);
            StringOperator stringOperator = StringOperator.toOperator(formula);
            boolean metCondition = satisfied(realValueString,stringOperator,dataValueString);
            if(metCondition){
                return satisfiedResult;
            }else{
                return reverse(satisfiedResult);
            }
        }else{
            // todo log
            return defaultResult;
        }
    }



    private static RuleResult simpleIntRule(Object realValue, Object dataValue, String formula, RuleResult satisfiedResult, RuleResult defaultResult) {
        boolean typeMatch = checkDataType(realValue,dataValue,1);
        if(typeMatch){
            int realValueInt = TypeUtil.toIntValue(realValue);
            int dataValueInt = TypeUtil.toIntValue(dataValue);
            IntOperator intOperator = IntOperator.toOperator(formula);
            boolean metCondition = satisfied(realValueInt,intOperator,dataValueInt);
            if(metCondition){
                return satisfiedResult;
            }else{
                return reverse(satisfiedResult);
            }
        }else{
            // todo log
            return defaultResult;
        }
    }

    private static boolean satisfied(int left, IntOperator operator, int right) {
        return  false;
    }
    private static boolean satisfied(String left, StringOperator operator, String right) {
        return false;
    }

    private static boolean checkDataType(Object realValue, Object dataValue, int dataType) {
        return  false;
    }

    private static RuleResult reverse(RuleResult satisfiedResult) {
        return RuleResult.ACCEPT;
    }

    public static RuleResult finalResult(Set<RuleResult> ruleResults){
        if(ruleResults.contains(RuleResult.REJECT)){
            return RuleResult.REJECT;
        }else if(ruleResults.contains(RuleResult.REVIEW)){
            return RuleResult.REVIEW;
        }else{
            return RuleResult.ACCEPT;
        }
    }
}
