package com.example.user_service.controller;


import com.example.user_service.dto.UserDTO;
import com.example.user_service.dto.UserUpdateDTO;
import com.example.user_service.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private UserService userService;

    @PutMapping("/profile")
    @PreAuthorize("hasRole('PLAYER')")
    public ResponseEntity<UserDTO> updateProfile(@Valid @RequestBody UserUpdateDTO userUpdateDTO,
                                                 Authentication authentication) {
        String username = authentication.getName();//iz ulogovanog coveka !!!
        System.out.println("Authorities: " + authentication.getName());
        System.out.println("UserUpdateDTO: " + userUpdateDTO.getUsername());
        UserDTO updatedUser = userService.updateUser(username, userUpdateDTO); //znaci ovde uzimamo iz bodija podatke
        return ResponseEntity.ok(updatedUser);
    }
}

