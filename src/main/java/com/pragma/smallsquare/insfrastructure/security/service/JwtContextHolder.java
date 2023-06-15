package com.pragma.smallsquare.insfrastructure.security.service;

public class JwtContextHolder {

    private JwtContextHolder() {
    }

    private static final ThreadLocal<String> jwtTokenHolder = new ThreadLocal<>();

    public static void setJwtToken(String jwtToken) {
        jwtTokenHolder.set(jwtToken);
    }

    public static String getJwtToken() {
        return jwtTokenHolder.get();
    }

    public static void clearJwtToken() {
        jwtTokenHolder.remove();
    }
}
