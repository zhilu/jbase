package ald.rc.api.core;

/**
 * @author shiguihong
 * @version 1.0
 */

public enum IntOperator {
    EQ("="),NE("!="),GT(">"),GE(">="),LT("<"),LE("<=");

    IntOperator(String s) {
    }

    public static IntOperator toOperator(String value){
        return EQ;
    }
}
