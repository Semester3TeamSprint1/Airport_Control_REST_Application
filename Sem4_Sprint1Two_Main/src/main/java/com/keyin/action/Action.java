package com.keyin.action;

import java.util.Map;

public class Action {
    private String object;
    private String operation;
    private Map<String, Object> parameters;

    public Action(String object, String operation, Map<String, Object> parameters) {
        this.object = object;
        this.operation = operation;
        this.parameters = parameters;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}