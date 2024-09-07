package ru.ruslan.animals.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString(exclude = "animals")
@SequenceGenerator(name = "default_id_gen", sequenceName = "owner_id_seq", allocationSize = 1)
@Table(name = "owner")
public class Owner extends User {

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "city", length = 50)
    private String city;

    @OneToMany(mappedBy = "owner")
    private Set<Animal> animals = new LinkedHashSet<>();
}
