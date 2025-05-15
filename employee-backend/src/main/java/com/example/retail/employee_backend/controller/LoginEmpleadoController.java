package com.example.retail.employee_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.employee_backend.dto.AuthResponseDTO;
import com.example.retail.employee_backend.dto.LoginRequestDTO;
import com.example.retail.employee_backend.security.JWTGenerator;

@RestController
public class LoginEmpleadoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;
    /* 
    @GetMapping("login-empleado")
    public String showLoginPage() {
        return "login_empleado";
    }*/

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginEmpleado(@RequestBody LoginRequestDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            String token = jwtGenerator.generateToken(authentication);

            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(403).body(null);
        }
    }
}
