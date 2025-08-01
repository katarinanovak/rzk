package com.example.tournament_service.dto;

import java.time.LocalDate;
import java.util.Date;

public class UserMatchScoreDto {
    private Long matchId;
    private Long tournamentId;
    private Date matchDate;
    private Integer score;       // može biti pointsWon ako želiš da koristiš isto polje
    private Integer pointsWon;   // dodatno ako želiš direktno da vidiš iz match-service

    public UserMatchScoreDto() {
    }

    public UserMatchScoreDto(Long matchId, Long tournamentId, Date matchDate, Integer score, Integer pointsWon) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.matchDate = matchDate;
        this.score = score;
        this.pointsWon = pointsWon;
    }



    public UserMatchScoreDto(Long matchId, Long tournamentId, Date matchDate, Integer score) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.matchDate = matchDate;
        this.score = score;
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

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPointsWon() {
        return pointsWon;
    }

    public void setPointsWon(Integer pointsWon) {
        this.pointsWon = pointsWon;
    }
}