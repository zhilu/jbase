package ald.rc.api.core;

/**
 * @author shiguihong
 * @version 1.0
 */

public interface RuleResult {
    RuleResult ACCEPT = new RuleResult() {
    };

    RuleResult REVIEW = new RuleResult() {
    };

    RuleResult REJECT = new RuleResult() {
    };
}
