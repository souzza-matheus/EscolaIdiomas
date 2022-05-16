package com.esof.escolaesof.exception;

public class AlunoNotFoundException extends Exception{
    public AlunoNotFoundException(Long matricula) {
        super(String.format("Aluno com matricula %s não encontrado.", matricula));
    }
}