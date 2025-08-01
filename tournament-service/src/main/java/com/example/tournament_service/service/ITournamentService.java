package com.example.tournament_service.service;

import com.example.tournament_service.dto.UserTournamentMatchScoreDto;

import java.util.List;

public interface ITournamentService {
    List<UserTournamentMatchScoreDto> getUserTournamentsWithScores(Long userId);
}

