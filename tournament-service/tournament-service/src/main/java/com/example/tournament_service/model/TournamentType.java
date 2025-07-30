package com.example.tournament_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tournament_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TournamentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}
