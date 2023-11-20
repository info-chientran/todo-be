package com.todo.cmmn.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private boolean success;
    private String message;
    private Object data;
}
