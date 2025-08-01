package com.example.match_service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_id")
    private Long tournamentId;
    @Column(name = "date")
    private LocalDate matchDate;


    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;// npr. SCHEDULED, ONGOING, FINISHED

//    private Integer round;
//
//    private Long winnerParticipantId;

    public Match() {
    }

    public Match(Long id, Long tournamentId, LocalDate matchDate, String status,String description) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.matchDate = matchDate;
        this.status = status;
        this.description = description;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}