package com.example.tournament_service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "participants")
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

    public Participant() {
    }

    public Participant(Long id, Long userId, Tournament tournament, LocalDate joinedAt, Integer score) {
        this.id = id;
        this.userId = userId;
        this.tournament = tournament;
        this.joinedAt = joinedAt;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDate joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}