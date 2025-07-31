package com.example.match_service.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "match_participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId;

    private Long participantId;

    private Integer score;

    private Boolean isWinner;
}
