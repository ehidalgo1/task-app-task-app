package com.taskapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_task_seq")
    @SequenceGenerator(sequenceName = "id_task_seq", name = "id_task_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type type;
    private Long idUser;
    private String resume;
    private int idPriority;
    private int idStatus;
    private LocalTime timeTask;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assignedAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resolveAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireIn;

    private boolean resolved;
    private boolean expired;

    public Task() {
    }

    public Task(Long id, String uuid, Type type, Long idUser, String resume, int idPriority, int idStatus, LocalTime timeTask, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime assignedAt, LocalDateTime resolveAt, LocalDateTime expireIn, boolean resolved, boolean expired) {
        this.id = id;
        this.uuid = uuid;
        this.type = type;
        this.idUser = idUser;
        this.resume = resume;
        this.idPriority = idPriority;
        this.idStatus = idStatus;
        this.timeTask = timeTask;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.assignedAt = assignedAt;
        this.resolveAt = resolveAt;
        this.expireIn = expireIn;
        this.resolved = resolved;
        this.expired = expired;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getIdPriority() {
        return idPriority;
    }

    public void setIdPriority(int idPriority) {
        this.idPriority = idPriority;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public LocalTime getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(LocalTime timeTask) {
        this.timeTask = timeTask;
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

    public LocalDateTime getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(LocalDateTime expireIn) {
        this.expireIn = expireIn;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", type=" + type +
                ", idUser=" + idUser +
                ", resume='" + resume + '\'' +
                ", idPriority=" + idPriority +
                ", idStatus=" + idStatus +
                ", timeTask=" + timeTask +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", assignedAt=" + assignedAt +
                ", resolveAt=" + resolveAt +
                ", expireIn=" + expireIn +
                ", resolved=" + resolved +
                ", expired=" + expired +
                '}';
    }
}
