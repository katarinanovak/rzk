package com.example.tournament_service.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // user iz user-service

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    private LocalDate joinedAt;

    private Integer score; // ako se koristi
}

