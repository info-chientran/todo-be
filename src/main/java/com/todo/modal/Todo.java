package com.todo.modal;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Entity
@Data
@RestControllerAdvice
@Table(name = "todo")
public class Todo  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "is_completed")
    private boolean isCompleted;
}
