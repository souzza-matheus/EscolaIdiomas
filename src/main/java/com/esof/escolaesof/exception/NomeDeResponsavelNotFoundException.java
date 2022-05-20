package com.esof.escolaesof.exception;

public class NomeDeResponsavelNotFoundException extends Exception{
    public NomeDeResponsavelNotFoundException() {
        super("Nome do responsavel é obrigatorio para alunos com menos de 18 anos");
    }
}
