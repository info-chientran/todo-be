package com.todo.controller;

import com.todo.cmmn.base.ResourceNotFoundException;
import com.todo.cmmn.base.Response;
import com.todo.modal.Todo;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    ResponseEntity<Response> createTodo(@RequestBody Todo todo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response(true,
                            "Todo created successfully",
                            todoService.createTodo(todo)));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body((new Response(false, "Error created todo", null)));
        }
    }

    @GetMapping()
    ResponseEntity<Response> getAllTodo() {
        try {
            return ResponseEntity.ok(new Response(true,
                    "Todo retrieved successfully",
                    todoService.getAllTodo()));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body((new Response(false, "Error retrieving todo", null)));
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Response> getTodoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response(true,
                    "Todo retrieved successfully: ",
                    todoService.getTodoById(id)));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, "Error retrieving todo with id: " + id, null));
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Response> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        try {
            return ResponseEntity.ok(new Response(true,
                    "Todo update successfully",
                    todoService.updateTodo(id, todo)));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, "Error updating todo with id: " + id, null));
        }
    }

    @DeleteMapping()
    ResponseEntity<Response> deleteAllTodo() {
        try {
            return ResponseEntity.ok(new Response(true,
                    "Todo list deleted successfully",
                    todoService.deleteAllTodo()));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(false,
                            "Error deleting todo with id: ",
                            null));
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteTodo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response(true,
                    "Todo deleted successfully",
                    todoService.deleteTodoById(id)));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(false,
                            "Error deleting todo with id: " + id,
                            null));
        }
    }
}
