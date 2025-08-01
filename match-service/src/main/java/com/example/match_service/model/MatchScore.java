package com.example.match_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "match_scores")
public class MatchScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "games_won")
    private Integer gamesWon;

    @Column(name = "points_won")
    private Integer pointsWon;

    public MatchScore() {
    }

    public MatchScore(Long id, Long matchId, Long playerId, Integer setNumber, Integer gamesWon, Integer pointsWon) {
        this.id = id;
        this.matchId = matchId;
        this.playerId = playerId;
        this.setNumber = setNumber;
        this.gamesWon = gamesWon;
        this.pointsWon = pointsWon;
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

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(Integer setNumber) {
        this.setNumber = setNumber;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Integer getPointsWon() {
        return pointsWon;
    }

    public void setPointsWon(Integer pointsWon) {
        this.pointsWon = pointsWon;
    }
}