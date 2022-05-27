package com.taskapp.model;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
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
    private int idType;
    private Long idUser;
    private String resume;
    private int idPriority;
    private int idStatus;
    private LocalTime timeTask;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolveAt;

    public Task() {

    }

    public Task(Long id, String uuid, int idType, Long idUser, String resume, int idPriority, int idStatus, LocalTime timeTask, Date createAt, Date updateAt, Date assignedAt, Date resolveAt) {
        this.id = id;
        this.uuid = uuid;
        this.idType = idType;
        this.idUser = idUser;
        this.resume = resume;
        this.idPriority = idPriority;
        this.idStatus = idStatus;
        this.timeTask = timeTask;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.assignedAt = assignedAt;
        this.resolveAt = resolveAt;
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

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Date assignedAt) {
        this.assignedAt = assignedAt;
    }

    public Date getResolveAt() {
        return resolveAt;
    }

    public void setResolveAt(Date resolveAt) {
        this.resolveAt = resolveAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", idType=" + idType +
                ", idUser=" + idUser +
                ", resume='" + resume + '\'' +
                ", idPriority=" + idPriority +
                ", idStatus=" + idStatus +
                ", timeTask=" + timeTask +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", assignedAt=" + assignedAt +
                ", resolveAt=" + resolveAt +
                '}';
    }
}
