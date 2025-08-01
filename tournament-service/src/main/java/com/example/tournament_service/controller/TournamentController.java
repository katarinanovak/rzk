package com.example.tournament_service.controller;

import com.example.tournament_service.dto.TournamentDto;
import com.example.tournament_service.dto.UserTournamentMatchScoreDto;
import com.example.tournament_service.model.Tournament;
import com.example.tournament_service.service.TournamentService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<Tournament> createTournament(@Valid @RequestBody TournamentDto tournamentDto) {
        Tournament created = tournamentService.createTournament(tournamentDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id, @RequestParam Long organizerId) {
        tournamentService.deleteTournament(id, organizerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<List<Tournament>> getAllTournamentsByOrganizer(@RequestParam Long organizerId) {
        List<Tournament> tournaments = tournamentService.getAllTournamentsByOrganizer(organizerId);
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<Tournament> getTournamentByIdForOrganizer(@PathVariable Long id, @RequestParam Long organizerId) {
        Tournament tournament = tournamentService.getTournamentByIdAndOrganizer(id, organizerId);
        return ResponseEntity.ok(tournament);
    }



    @GetMapping("/user/{userId}/tournaments-with-scores")
    @PreAuthorize("hasAuthority('PLAYER')")
    public ResponseEntity<?> getUserTournamentsWithScores(@PathVariable Long userId) {
        if (userId <= 0) {
            return ResponseEntity.badRequest().body("Invalid user ID.");
        }

        try {
            List<UserTournamentMatchScoreDto> data = tournamentService.getUserTournamentsWithScores(userId);
            if (data.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(data);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Error communicating with match-service: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/name")
    @PreAuthorize("hasAuthority('PLAYER')")
    public ResponseEntity<String> getTournamentName(@PathVariable Long id) {
        String name = tournamentService.getTournamentNameById(id);
        return ResponseEntity.ok(name);
    }



//    @GetMapping
//    @PreAuthorize("hasAuthority('PLAYER')")
//    public ResponseEntity<List<String>> getAllUsers(Authentication authentication) {
//        System.out.println("Authorities: " + authentication.getAuthorities());
//        return ResponseEntity.ok(List.of("user1", "user2", "user3"));
//    }



}


