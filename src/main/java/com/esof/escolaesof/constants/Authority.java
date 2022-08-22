package com.esof.escolaesof.constants;


public class Authority{
    public static final String[] USER_AUTHORITIES = { "user:read", "aluno:read", "curso:read","professor:read" };
    public static final String[] ADMIN_AUTHORITIES = { "user:read", "aluno:read", "curso:read","professor:read" ,
            "user:create","curso:create","professor:create",
            "user:update","curso:update","professor:update", "user:delete","curso:delete","professor:delete"};
}