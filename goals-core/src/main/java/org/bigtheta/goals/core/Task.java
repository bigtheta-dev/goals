package org.bigtheta.goals.core;


public class Task {

    private String id;
    private String value;
    private TaskStatus status;

    public Task() {
    }

    public Task(String id, String value) {
        this.id = id;
        this.value = value;
        this.status = TaskStatus.ACTIVE;
    }

    public Task(String id, String value, TaskStatus status) {
        this.id = id;
        this.value = value;
        this.status = status;
    }
    public Task(String value) {
        this.value = value;
        this.status = TaskStatus.ACTIVE;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return id + "\t" + value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
