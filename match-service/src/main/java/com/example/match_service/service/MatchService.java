package com.example.match_service.service;

import com.example.match_service.dto.PlayerMatchDTO;
import com.example.match_service.dto.UserMatchScoreDto;
import com.example.match_service.feign.TournamentClient;
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

    private final TournamentClient tournamentClient;

    public MatchService(MatchParticipantRepository matchParticipantRepo,
                        MatchScoreRepository matchScoreRepo,
                        MatchRepository matchRepo,
                        TournamentClient tournamentClient) {
        this.matchParticipantRepo = matchParticipantRepo;
        this.matchScoreRepo = matchScoreRepo;
        this.matchRepo = matchRepo;
        this.tournamentClient = tournamentClient;
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

    public List<PlayerMatchDTO> getScheduleForUser(Long userId) {
        List<MatchParticipant> participants = matchParticipantRepo.findByUserId(userId);

        if (participants.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> matchIds = participants.stream()
                .map(MatchParticipant::getMatchId)
                .distinct()
                .collect(Collectors.toList());

        List<Match> matches = matchRepo.findAllById(matchIds);

        List<PlayerMatchDTO> schedule = new ArrayList<>();

        for (Match match : matches) {
            String tournamentName = "Unknown Tournament";
            try {
                tournamentName = tournamentClient.getTournamentName(match.getTournamentId());
            } catch (Exception ignored) {}

            PlayerMatchDTO dto = new PlayerMatchDTO(
                    match.getId(),
                    match.getMatchDate(),
                    match.getStatus(),
                    match.getDescription(),
                    tournamentName
            );
            schedule.add(dto);
        }


        schedule.sort(Comparator.comparing(PlayerMatchDTO::getMatchDate));

        return schedule;
    }

}
