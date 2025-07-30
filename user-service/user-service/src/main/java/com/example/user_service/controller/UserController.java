package com.example.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<String>> getAllUsers() {
        // za testiranje, vrati jednostavnu listu user imena
        List<String> users = List.of("user1", "user2", "user3");
        return ResponseEntity.ok(users);
    }
}
