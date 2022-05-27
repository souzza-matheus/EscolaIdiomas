package com.esof.escolaesof.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @Column(nullable = false, updatable = false)
    private String codigo;

    private String nome;

    private String idioma;

    private String nivel;

    private String turno;

    @OneToMany(mappedBy = "curso",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Aluno> alunos;

    @OneToOne(mappedBy = "curso")
    private Professor professor;

}

