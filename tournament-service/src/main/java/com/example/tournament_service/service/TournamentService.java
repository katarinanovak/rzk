package com.example.tournament_service.service;

import com.example.tournament_service.dto.TournamentDto;
import com.example.tournament_service.dto.UserMatchScoreDto;
import com.example.tournament_service.dto.UserTournamentMatchScoreDto;
import com.example.tournament_service.exception.UserNotFoundException;
import com.example.tournament_service.feign.MatchServiceFeignClient;
import com.example.tournament_service.model.Participant;
import com.example.tournament_service.model.Tournament;
import com.example.tournament_service.model.TournamentType;
import com.example.tournament_service.repository.ParticipantRepository;
import com.example.tournament_service.repository.TournamentRepository;
import com.example.tournament_service.repository.TournamentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TournamentService implements ITournamentService {
    private final ParticipantRepository participantRepo;

    private final MatchServiceFeignClient matchFeign;

    private final TournamentRepository tournamentRepository;
    private final TournamentTypeRepository tournamentTypeRepository;

    public TournamentService(ParticipantRepository participantRepo, MatchServiceFeignClient matchFeign, TournamentRepository tournamentRepository,
                             TournamentTypeRepository tournamentTypeRepository) {
        this.participantRepo = participantRepo;
        this.matchFeign = matchFeign;
        this.tournamentRepository = tournamentRepository;
        this.tournamentTypeRepository = tournamentTypeRepository;
    }

    public Tournament createTournament(TournamentDto dto) {
        Tournament tournament = new Tournament();

        tournament.setName(dto.getName());
        tournament.setDescription(dto.getDescription());
        tournament.setStartDate(dto.getStartDate());
        tournament.setEndDate(dto.getEndDate());
        tournament.setStatus(dto.getStatus());
        tournament.setOrganizerId(dto.getOrganizerId());

        // Uzimamo tip turnira iz baze po ID-ju, ili bacamo izuzetak ako ne postoji
        Optional<TournamentType> tournamentTypeOpt = tournamentTypeRepository.findById(dto.getTournamentTypeId());
        if (tournamentTypeOpt.isEmpty()) {
            throw new IllegalArgumentException("Ne postoji tip turnira sa ID: " + dto.getTournamentTypeId());
        }
        tournament.setTournamentType(tournamentTypeOpt.get());

        // participants polje može ostati prazno na kreiranju
        tournament.setParticipants(null);

        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Long tournamentId, Long organizerId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Turnir sa ID " + tournamentId + " ne postoji"));

        if (!tournament.getOrganizerId().equals(organizerId)) {
            throw new SecurityException("Nemate pravo da obrišete ovaj turnir");
        }

        tournamentRepository.delete(tournament);
    }

    public List<Tournament> getAllTournamentsByOrganizer(Long organizerId) {
        return tournamentRepository.findAllByOrganizerId(organizerId);
    }

    public Tournament getTournamentByIdAndOrganizer(Long tournamentId, Long organizerId) {
        return tournamentRepository.findByIdAndOrganizerId(tournamentId, organizerId)
                .orElseThrow(() -> new IllegalArgumentException("Turnir ne postoji ili nemate pristup"));
    }


    @Override
    public List<UserTournamentMatchScoreDto> getUserTournamentsWithScores(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        List<Participant> participations = participantRepo.findByUserId(userId);
        if (participations.isEmpty()) {
            throw new UserNotFoundException("Korisnik sa ID " + userId + " nije učestvovao ni u jednom turniru.");
        }

        List<UserMatchScoreDto> feignScores = matchFeign.getUserScores(userId).getBody();
        if (feignScores == null) feignScores = new ArrayList<>();

        List<UserMatchScoreDto> userScores = feignScores.stream().map(dto ->
                new UserMatchScoreDto(
                        dto.getMatchId(),
                        dto.getTournamentId(),
                        dto.getMatchDate(),     // koristi direktno ako je već Date
                        dto.getPointsWon()      // koristi kao score
                )
        ).collect(Collectors.toList());

        Map<Long, List<UserMatchScoreDto>> scoresByTournament = userScores.stream()
                .collect(Collectors.groupingBy(UserMatchScoreDto::getTournamentId));

        List<UserTournamentMatchScoreDto> result = new ArrayList<>();

        for (Participant p : participations) {
            if (p.getTournament() == null) continue;

            Long tournamentId = p.getTournament().getId();
            Tournament t = tournamentRepository.findById(tournamentId).orElse(null);
            if (t == null) continue;

            List<UserMatchScoreDto> scores = scoresByTournament.getOrDefault(t.getId(), new ArrayList<>());
            result.add(new UserTournamentMatchScoreDto(t.getName(), scores));
        }

        return result;
    }
}