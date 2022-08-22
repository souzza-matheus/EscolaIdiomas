package com.esof.escolaesof.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String username){
        super(String.format("Employee with username %s not found in the system.",username));
    }

    public UserNotFoundException(Long id){
        super(String.format("Employee with id %s not found in the system.",id));
    }
}
