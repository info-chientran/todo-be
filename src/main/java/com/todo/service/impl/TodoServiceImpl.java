package com.todo.service.impl;

import com.todo.cmmn.base.ResourceNotFoundException;
import com.todo.modal.Todo;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id" + id));
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
            Todo todoUpdate = todoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

            todoUpdate.setTitle(todo.getTitle());
            todoUpdate.setCompleted(todo.isCompleted());

            return todoRepository.save(todoUpdate);
    }

    @Override
    public Todo deleteAllTodo() {
        List<Todo> todo = todoRepository.findAll();
        todoRepository.deleteAll(todo);
        return null;
    }

    @Override
    public Todo deleteTodoById(Long id) {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
            todoRepository.delete(todo);
            return null;
    }
}
