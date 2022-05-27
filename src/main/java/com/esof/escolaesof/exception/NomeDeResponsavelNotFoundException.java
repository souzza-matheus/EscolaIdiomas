package com.esof.escolaesof.exception;

public class NomeDeResponsavelNotFoundException extends RuntimeException{
    public NomeDeResponsavelNotFoundException() {
        super("Nome do responsavel é obrigatorio para alunos com menos de 18 anos");
    }
}
