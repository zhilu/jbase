package ald.rc.api.core;

public enum StringOperator {

    EQUAL("EQUAL"),UNEQUAL("UNEQUAL"),START("START"),NE("END"),INCLUDE("INCLUDE"),EXCLUDE("EXCLUDE"),REGEXP("REGEXP");

    StringOperator(String s) {
    }

    public static StringOperator toOperator(String value){
        return EQUAL;
    }
}
