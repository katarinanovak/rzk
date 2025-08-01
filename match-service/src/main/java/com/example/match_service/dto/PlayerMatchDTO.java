package com.example.match_service.dto;

import java.time.LocalDate;

public class PlayerMatchDTO {
    private Long matchId;
    private LocalDate matchDate;
    private String status;
    private String description;
    private String tournamentName;

    public PlayerMatchDTO() {}

    public PlayerMatchDTO(Long matchId, LocalDate matchDate, String status, String description, String tournamentName) {
        this.matchId = matchId;
        this.matchDate = matchDate;
        this.status = status;
        this.description = description;
        this.tournamentName = tournamentName;
    }

    public Long getMatchId() {
        return matchId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
