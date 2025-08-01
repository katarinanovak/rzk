package com.example.tournament_service.dto;

import java.util.List;

public class UserTournamentMatchScoreDto {
    private String tournamentName;
    private List<UserMatchScoreDto> scores;

    public UserTournamentMatchScoreDto() {
    }

    public UserTournamentMatchScoreDto(String tournamentName, List<UserMatchScoreDto> scores) {
        this.tournamentName = tournamentName;
        this.scores = scores;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public List<UserMatchScoreDto> getScores() {
        return scores;
    }

    public void setScores(List<UserMatchScoreDto> scores) {
        this.scores = scores;
    }
}

