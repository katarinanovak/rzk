package com.example.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // Endpoint dostupan samo korisnicima sa rolom ADMIN
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<String>> getAllUsers() {
        List<String> users = List.of("user1", "user2", "user3");
        return ResponseEntity.ok(users);
    }

    // Endpoint dostupan svim ulogovanim korisnicima
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getUserProfile() {
        return ResponseEntity.ok("Ovo je profil trenutno ulogovanog korisnika");
    }
}
