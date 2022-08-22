package com.esof.escolaesof.exception;

public class EmailNotFoundException extends Exception{
    public EmailNotFoundException(String email){
        super(String.format("Email %s not found in te system..",email));
    }
}
