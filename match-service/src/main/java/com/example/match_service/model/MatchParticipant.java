package com.example.match_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "participants")
public class MatchParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId;

    @Column(name = "user_id")
    private Long userId;

    private Integer score;

    @Column(name = "is_winner")
    private Boolean isWinner;

    public MatchParticipant() {
    }

    public MatchParticipant(Long id, Long matchId, Long userId, Integer score, Boolean isWinner) {
        this.id = id;
        this.matchId = matchId;
        this.userId = userId;
        this.score = score;
        this.isWinner = isWinner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getIsWinner() {
        return isWinner;
    }

    public void setIsWinner(Boolean isWinner) {
        this.isWinner = isWinner;
    }
}