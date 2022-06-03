package com.taskapp.dto;

import com.taskapp.model.Type;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskDto {

    private Long id;
    private String uuid;
    private Type type;
    private UserDto user;
    private String resume;
    private String status;
    private String timeTask;
    private LocalDateTime assignedAt;
    private LocalDateTime resolveAt;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public TaskDto() {
    }

    public TaskDto(Long id, String uuid, Type type, UserDto user, String resume, String status, String timeTask, LocalDateTime assignedAt, LocalDateTime resolveAt, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.uuid = uuid;
        this.type = type;
        this.user = user;
        this.resume = resume;
        this.status = status;
        this.timeTask = timeTask;
        this.assignedAt = assignedAt;
        this.resolveAt = resolveAt;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public LocalDateTime getResolveAt() {
        return resolveAt;
    }

    public void setResolveAt(LocalDateTime resolveAt) {
        this.resolveAt = resolveAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", type=" + type +
                ", user=" + user +
                ", resume='" + resume + '\'' +
                ", status='" + status + '\'' +
                ", timeTask='" + timeTask + '\'' +
                ", assignedAt=" + assignedAt +
                ", resolveAt=" + resolveAt +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
