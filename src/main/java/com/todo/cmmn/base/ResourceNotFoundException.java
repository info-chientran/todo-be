package com.todo.cmmn.base;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String messsage) {
        super(messsage);
    }
}
