package com.example.match_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private LocalDateTime matchDate;

    private String status;  // npr. SCHEDULED, ONGOING, FINISHED

    private Integer round;

    private Long winnerParticipantId;
}
