package ru.job4j.todo.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity()
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private  int id;

    @NonNull
    private  String email;

    private  String name;

    @NonNull
    private  String password;

    @Column(name = "user_zone")
    private String timezone;
}