package com.esof.escolaesof.constants;


public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 dias em milisegungos
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String FICTIONAL_LLC = "Escola Idiomas , LLC";
    public static final String ADMINISTRATION = "Escola Idiomas";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = { "/auth, /register"};
}
