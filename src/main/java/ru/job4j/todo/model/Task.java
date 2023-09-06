package ru.job4j.todo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity()
@Table(name = "tasks")
public class Task {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private LocalDateTime created;
    @Getter
    @Setter
    private boolean done;
}