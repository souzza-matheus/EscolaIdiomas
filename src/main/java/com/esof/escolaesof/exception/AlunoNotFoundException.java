package com.esof.escolaesof.exception;

public class AlunoNotFoundException extends RuntimeException{
    public AlunoNotFoundException(Long matricula) {
        super(String.format("Aluno com matricula %s não encontrado.", matricula));
    }
}