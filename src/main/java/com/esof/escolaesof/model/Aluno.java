package com.esof.escolaesof.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Column(nullable = false)
    private String curso;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    private String nome_responsavel;

    private String telefone_responsavel;

    private String email_responsavel;

}