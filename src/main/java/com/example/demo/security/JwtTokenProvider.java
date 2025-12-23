package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(String email) {
        return "jwt-token";
    }

    public boolean validateToken(String token) {
        return token != null && token.equals("jwt-token");
    }

    public String getEmailFromToken(String token) {
        return "user@example.com";
    }
}
