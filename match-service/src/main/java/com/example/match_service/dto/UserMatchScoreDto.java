package com.example.match_service.dto;

import java.time.LocalDate;
import java.util.Date;

public class UserMatchScoreDto {
    private Long matchId;
    private Long tournamentId;
    private LocalDate matchDate;
    private Integer gamesWon;    // umesto "score"
    private Integer pointsWon;

    public UserMatchScoreDto() {
    }

    public UserMatchScoreDto(Long matchId, Long tournamentId, LocalDate matchDate, Integer gamesWon, Integer pointsWon) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.matchDate = matchDate;
        this.gamesWon = gamesWon;
        this.pointsWon = pointsWon;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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
