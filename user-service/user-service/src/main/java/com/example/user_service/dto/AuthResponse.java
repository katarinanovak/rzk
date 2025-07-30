package com.example.user_service.dto;




public class AuthResponse {

    private String token;

    public AuthResponse() {
        // default constructor
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}