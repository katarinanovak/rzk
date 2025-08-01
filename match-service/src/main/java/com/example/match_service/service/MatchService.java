package com.example.match_service.service;

import com.example.match_service.dto.UserMatchScoreDto;
import com.example.match_service.model.Match;
import com.example.match_service.model.MatchParticipant;
import com.example.match_service.model.MatchScore;
import com.example.match_service.repository.MatchParticipantRepository;
import com.example.match_service.repository.MatchRepository;
import com.example.match_service.repository.MatchScoreRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchParticipantRepository matchParticipantRepo;
    private final MatchScoreRepository matchScoreRepo;
    private final MatchRepository matchRepo;

    public MatchService(MatchParticipantRepository matchParticipantRepo,
                        MatchScoreRepository matchScoreRepo,
                        MatchRepository matchRepo) {
        this.matchParticipantRepo = matchParticipantRepo;
        this.matchScoreRepo = matchScoreRepo;
        this.matchRepo = matchRepo;
    }

    public List<UserMatchScoreDto> getScoresForUser(Long userId) {
        List<MatchParticipant> participations = matchParticipantRepo.findByUserId(userId);

        return participations.stream()
                .map(mp -> {
                    List<MatchScore> scores = matchScoreRepo.findByMatchIdAndPlayerId(mp.getId(), userId);
                    Optional<Match> matchOpt = matchRepo.findById(mp.getId());

                    if (!scores.isEmpty() && matchOpt.isPresent()) {
                        Match match = matchOpt.get();


                        MatchScore score = scores.get(0);

                        return new UserMatchScoreDto(
                                match.getId(),
                                match.getTournamentId(),
                                match.getMatchDate(),
                                score.getGamesWon(),
                                score.getPointsWon()
                        );
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
