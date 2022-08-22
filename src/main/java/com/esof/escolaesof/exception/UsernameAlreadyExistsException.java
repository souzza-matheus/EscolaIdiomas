package com.esof.escolaesof.exception;

public class UsernameAlreadyExistsException extends Exception{

    public UsernameAlreadyExistsException(String username){
        super(String.format("Username %s already registered in the system.",username));
    }
}
