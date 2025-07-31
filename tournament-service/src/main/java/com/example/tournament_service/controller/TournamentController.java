package com.example.tournament_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @GetMapping
   @PreAuthorize("hasAuthority('PLAYER')")
    public ResponseEntity<List<String>> getAllUsers(Authentication authentication) {
        System.out.println("Authorities: " + authentication.getAuthorities());
        return ResponseEntity.ok(List.of("user1", "user2", "user3"));
    }

}


