package ru.ruslan.animals.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_id_gen")
    @Column(name = "id")
    protected long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
