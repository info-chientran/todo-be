package com.todo.service;

import com.todo.modal.Todo;

import java.util.List;

public interface TodoService {
    Todo createTodo(Todo todo);

    List<Todo> getAllTodo();

    Todo getTodoById(Long id);

    Todo updateTodo(Long id, Todo todo);

    Todo deleteAllTodo();

    Todo deleteTodoById(Long id);
}
