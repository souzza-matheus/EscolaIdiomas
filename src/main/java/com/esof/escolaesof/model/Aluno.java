package com.esof.escolaesof.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long matricula;

    @Column(nullable = false)
    private String nome;

    @ManyToOne()
    @JoinColumn(name = "curso_codigo")
    private Curso curso;

    @Column(nullable = false)
    private int idade;

    private String email;

    private String telefone;

    private String nome_responsavel;

    private String telefone_responsavel;

    private String email_responsavel;

}