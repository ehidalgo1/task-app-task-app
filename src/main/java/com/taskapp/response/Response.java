package com.taskapp.response;

import com.taskapp.util.StatusEnum;

import java.time.LocalDateTime;

public class Response {

    private Enum<StatusEnum> status;
    private LocalDateTime date;
    private Object response;

    public Response() {
    }

    public Response(Enum<StatusEnum> status, Object response) {
        this.status = status;
        this.date = LocalDateTime.now();
        this.response = response;
    }

    public Enum<StatusEnum> getStatus() {
        return status;
    }

    public void setStatus(Enum<StatusEnum> status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", date=" + date +
                ", response=" + response +
                '}';
    }
}
