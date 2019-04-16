package com.cqz.model;

import java.util.Date;

public class Task {
    private Integer taskId;

    private String taskName;

    private String taskType;

    private String taskCharge;

    private String taskSelectPeople;

    private String taskRunner;

    private Date taskStartTime;

    private Date taskEndTime;

    private String taskStatus;

    private String taskDescription;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getTaskCharge() {
        return taskCharge;
    }

    public void setTaskCharge(String taskCharge) {
        this.taskCharge = taskCharge == null ? null : taskCharge.trim();
    }

    public String getTaskSelectPeople() {
        return taskSelectPeople;
    }

    public void setTaskSelectPeople(String taskSelectPeople) {
        this.taskSelectPeople = taskSelectPeople == null ? null : taskSelectPeople.trim();
    }

    public String getTaskRunner() {
        return taskRunner;
    }

    public void setTaskRunner(String taskRunner) {
        this.taskRunner = taskRunner == null ? null : taskRunner.trim();
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription == null ? null : taskDescription.trim();
    }
}