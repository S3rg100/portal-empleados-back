package com.example.retail.employee_backend.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTGenerator {

    
    private static final String SECRET_KEY_STRING = "u7vX8tE93NqR2kWp9sTg5vBjLtWqZmYc";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8));

    // Claim keys según el estándar WS-Federation
    private static final String CLAIM_USERNAME = "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name";
    private static final String CLAIM_ROLE = "http://schemas.microsoft.com/ws/2008/06/identity/claims/role";

    public String extractUsername(String token) {
        return getAllClaimsFromToken(token).get(CLAIM_USERNAME, String.class);
    }

    public List<String> extractRoles(String token) {
        Claims claims = getAllClaimsFromToken(token);

        Object raw = claims.get(CLAIM_ROLE);
        if (raw instanceof String roleStr) {
            return List.of(roleStr);  // Un solo rol
        } else if (raw instanceof List<?> rolesList) {
            return rolesList.stream().map(Object::toString).toList(); // Múltiples roles
        }
        return Collections.emptyList();
    }

    public boolean validateToken(String token) {
        try {
            getAllClaimsFromToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
