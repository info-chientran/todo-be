package com.todo.controller;

import com.todo.cmmn.base.ResourceNotFoundException;
import com.todo.cmmn.base.Response;
import com.todo.modal.Todo;
import com.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    ResponseEntity<Response> getAllTodo() {
        try {
            List<Todo> todo = todoRepository.findAll();
            return ResponseEntity.ok(new Response(true, "Todo retrieved successfully", todo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body((new Response(false, "Error retrieving todo", null)));
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Response> getTodoById(@PathVariable Long id) {
        try {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id" + id));

            return ResponseEntity.ok(new Response(true, "Todo retrieved successfully: ", todo));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, "Error retrieving todo with id: " + id, null));
        }
    }

    @PostMapping
    ResponseEntity<Response> createTodo(@RequestBody Todo todo) {
        try {
            Todo createdTodo = todoRepository.save(todo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response(true, "Todo created successfully", createdTodo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(false, "Error creating todo", null));
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Response> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetail) {
        try {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

            todo.setTitle(todoDetail.getTitle());
            todo.setCompleted(todoDetail.isCompleted());

            Todo updatedTodo = todoRepository.save(todo);
            return ResponseEntity.ok(new Response(true, "Todo update successfully", updatedTodo));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, "Error updating todo with id: " + id, null));
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteTodo(@PathVariable Long id) {
        try {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Todo deleted successfully" + id));

            todoRepository.delete(todo);
            return ResponseEntity.ok(new Response(true, "todo deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, "Error deleting todo with id: " + id, null));
        }
    }
}
