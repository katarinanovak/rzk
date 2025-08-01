package com.example.match_service.controller;

import com.example.match_service.dto.UserMatchScoreDto;
import com.example.match_service.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }


    @GetMapping("/user/{userId}/scores")
    @PreAuthorize("hasAuthority('PLAYER')")
    public ResponseEntity<List<UserMatchScoreDto>> getUserMatchScores(@PathVariable Long userId) {
        if (userId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        List<UserMatchScoreDto> scores = matchService.getScoresForUser(userId);
        if (scores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(scores);
    }
}

