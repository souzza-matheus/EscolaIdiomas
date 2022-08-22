package com.esof.escolaesof.exception;

public class EmailAlreadyExistsException extends Exception{

    public EmailAlreadyExistsException(String email){
        super(String.format("Email %s already exists in the system.",email));
    }
}
