package com.example.retail.employee_backend.dto;

public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    public AuthResponseDTO() {}
    
    public void setToken(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }
}
