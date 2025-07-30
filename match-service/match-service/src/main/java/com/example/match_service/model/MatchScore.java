package com.example.match_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "match_scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId;

    private Long participantId;

    private Integer setNumber;

    private Integer gamesWon;

    private Integer pointsWon;
}
