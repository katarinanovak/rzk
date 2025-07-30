package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Opciono, ako želiš dvosmernu vezu
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;
}

