package com.ApiExamen.ApiExamen.security.config;

public interface SecurityParameters {
    public static final long EXP_TIME = 10 * 24 * 60 * 60 * 1000;
    public static final String SECRET = "Secret!123";
    public static final String PREFIX = "Bearer ";

}