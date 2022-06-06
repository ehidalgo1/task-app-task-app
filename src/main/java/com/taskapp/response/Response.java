package com.taskapp.response;

import com.taskapp.util.StatusEnum;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private Enum<StatusEnum> status;
    private LocalDateTime date;
    private Map<String, Object> response;

    public Response() {
    }

    public Response(Enum<StatusEnum> status, String typeResponse, Object response) {
        this.status = status;
        this.date = LocalDateTime.now();
        Map<String, Object> resp = new HashMap<>();
        resp.put(typeResponse, response);
        this.response = resp;
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

    public Map<String, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<String, Object> response) {
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
