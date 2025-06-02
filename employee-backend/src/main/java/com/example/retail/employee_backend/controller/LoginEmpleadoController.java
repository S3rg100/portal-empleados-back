package com.example.retail.employee_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.retail.employee_backend.dto.AuthResponseDTO;
import com.example.retail.employee_backend.dto.LoginRequestDTO;
import com.example.retail.employee_backend.security.JWTGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginEmpleadoController {

    @Value("${external.auth.url}")
    private String authUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    /*
     * @GetMapping("login-empleado")
     * public String showLoginPage() {
     * return "login_empleado";
     * }
     */

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginEmpleado(@RequestBody LoginRequestDTO loginRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequestDTO> requestEntity = new HttpEntity<>(loginRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    authUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                String tokenWrapper = root.path("token").asText();

                if (tokenWrapper.startsWith("{") && tokenWrapper.contains("\"token\"")) {
                    JsonNode inner = mapper.readTree(tokenWrapper);
                    tokenWrapper = inner.path("token").asText();
                }

                return ResponseEntity.ok(new AuthResponseDTO(tokenWrapper));
            } else {
                return ResponseEntity.status(response.getStatusCode()).build();
            }

        } catch (HttpClientErrorException.Unauthorized ex) {
            // ⚠️ Error 401 del servidor remoto
            return ResponseEntity.status(401).body(new AuthResponseDTO("Credenciales incorrectas"));
        } catch (HttpClientErrorException ex) {
            // Otros errores 4xx
            return ResponseEntity.status(ex.getStatusCode()).body(new AuthResponseDTO("Error: " + ex.getMessage()));
        } catch (Exception ex) {
            // Otros errores no relacionados directamente
            return ResponseEntity.status(500).body(new AuthResponseDTO("Error interno: " + ex.getMessage()));
        }
    }

}
