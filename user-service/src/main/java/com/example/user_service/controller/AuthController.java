package com.example.user_service.controller;

import com.example.user_service.dto.AuthRequest;
import com.example.user_service.dto.AuthResponse;
import com.example.user_service.config.JwtUtil;
import com.example.user_service.model.User;
import com.example.user_service.service.CustomUserDetailsService;

import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private CustomUserDetailsService userDetailsService;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );


        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        User user = userService.findByUsername(authRequest.getUsername());
        Long userId = user.getId();

        //String token = jwtUtil.generateToken(userDetails);
        String token = jwtUtil.generateToken(userDetails, userId);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
