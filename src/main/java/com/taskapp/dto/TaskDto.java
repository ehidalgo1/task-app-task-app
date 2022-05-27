package com.taskapp.dto;

public class TaskDto {

    private Long id;
    private String uuid;
    private String type;
    private UserDto userDto;
    private String resume;
    private String status;
    private String timeTask;
    private String assignedAt;
    private String resolveAt;
    private String createAt;
    private String updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(String timeTask) {
        this.timeTask = timeTask;
    }

    public String getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(String assignedAt) {
        this.assignedAt = assignedAt;
    }

    public String getResolveAt() {
        return resolveAt;
    }

    public void setResolveAt(String resolveAt) {
        this.resolveAt = resolveAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", type='" + type + '\'' +
                ", userDto=" + userDto +
                ", resume='" + resume + '\'' +
                ", status='" + status + '\'' +
                ", timeTask='" + timeTask + '\'' +
                ", assignedAt='" + assignedAt + '\'' +
                ", resolveAt='" + resolveAt + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }
}
