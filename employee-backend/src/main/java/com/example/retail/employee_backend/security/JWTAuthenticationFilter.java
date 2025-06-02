package com.example.retail.employee_backend.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTGenerator jwtGenerator;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJWT(request);
        System.out.println("[DEBUG] Token recibido: " + token);

        if (token != null &&
            SecurityContextHolder.getContext().getAuthentication() == null &&
            jwtGenerator.validateToken(token)) {

            String username = jwtGenerator.extractUsername(token);
            System.out.println("[DEBUG] Username extraído: " + username);
            List<String> roles = jwtGenerator.extractRoles(token); // 👈 método nuevo
            System.out.println("[DEBUG] Roles extraídos: " + roles);

            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username, null, authorities);

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            System.out.println("[DEBUG] Token recibido: " + token);
            System.out.println("[DEBUG] Usuario autenticado: " + username);
            System.out.println("[DEBUG] Roles: " + roles);
        }

        filterChain.doFilter(request, response);
        
    }

    private String getJWT(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", ""); // Quitar "Bearer "
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/login") || path.startsWith("/h2") || path.startsWith("/login-empleado");
    }
}
