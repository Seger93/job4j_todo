package ru.job4j.todo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity()
@Table(name = "tasks")
public class Task {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Getter
    @Setter
    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    @Getter
    @Setter
    private boolean done;
}