package com.esof.escolaesof.exception;

import org.springframework.http.HttpStatus;

public class ContatoDeResponsavelNotFoundException extends RuntimeException{
    public ContatoDeResponsavelNotFoundException(HttpStatus code) {
        super("Informações de contato do responsavel são obrigatorias para alunos com menos de 18 anos");
    }
}
