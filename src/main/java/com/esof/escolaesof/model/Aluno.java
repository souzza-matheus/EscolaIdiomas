package com.esof.escolaesof.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;


@Data
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long matricula;

    @Column(nullable = false)
    private String nome;

    @ManyToOne()
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Column(nullable = false)
    private int idade;

    private String email;

    private String telefone;

    private String nome_responsavel;

    private String telefone_responsavel;

    private String email_responsavel;

    @JsonCreator
    public Aluno(Long matricula, String nome, Curso curso, int idade, String email, String telefone, String nome_responsavel, String telefone_responsavel, String email_responsavel) {
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
        this.email = email;
        this.telefone = telefone;
        this.nome_responsavel = nome_responsavel;
        this.telefone_responsavel = telefone_responsavel;
        this.email_responsavel = email_responsavel;
    }

    public Aluno (){}
}