package com.taskapp.dto;

import java.util.Date;

public class UserDto {

    private Long id;
    private String uuid;
    private String firstname;
    private String lastname;
    private String username;
    private Date createAt;
    private Date updateAt;

    public UserDto() {
    }

    public UserDto(Long id, String uuid, String firstname, String lastname, String username, Date createAt, Date updateAt) {
        this.id = id;
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
