package com.esof.escolaesof.exception;

public class ContatoDeResponsavelNotFoundException extends Exception{
    public ContatoDeResponsavelNotFoundException() {
        super("Informações de contato do responsavel são obrigatorias para alunos com menos de 18 anos");
    }
}
