package com.example.tournament_service.service;

import com.example.tournament_service.dto.TournamentDto;
import com.example.tournament_service.model.Tournament;
import com.example.tournament_service.model.TournamentType;
import com.example.tournament_service.repository.TournamentRepository;
import com.example.tournament_service.repository.TournamentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentTypeRepository tournamentTypeRepository;

    public TournamentService(TournamentRepository tournamentRepository,
                             TournamentTypeRepository tournamentTypeRepository) {
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

    // U TournamentService
    public List<Tournament> getAllTournamentsByOrganizer(Long organizerId) {
        return tournamentRepository.findAllByOrganizerId(organizerId);
    }

    public Tournament getTournamentByIdAndOrganizer(Long tournamentId, Long organizerId) {
        return tournamentRepository.findByIdAndOrganizerId(tournamentId, organizerId)
                .orElseThrow(() -> new IllegalArgumentException("Turnir ne postoji ili nemate pristup"));
    }



}