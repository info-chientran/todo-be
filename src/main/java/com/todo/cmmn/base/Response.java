package com.todo.cmmn.base;

import lombok.Data;

@Data
public class Response {
    private boolean success;
    private String message;
    private Object data;

    public Response(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
