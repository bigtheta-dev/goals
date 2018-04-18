package org.bigtheta.goals.core;


public class Task {

    private String value;

    public Task() {
    }

    public Task(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
