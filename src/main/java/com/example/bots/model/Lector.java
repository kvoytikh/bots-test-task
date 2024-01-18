package com.example.bots.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@Table(name = "lectors")
@ToString
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @ManyToMany
    @JoinTable(
            name = "lector_department",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments;
}
