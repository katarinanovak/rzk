package com.example.tournament_service.dto;

import java.util.Date;

public class UserMatchScoreDto {
    private Long matchId;
    private Long tournamentId;
    private Date matchDate;
    private Integer score;

    public UserMatchScoreDto() {
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
}

