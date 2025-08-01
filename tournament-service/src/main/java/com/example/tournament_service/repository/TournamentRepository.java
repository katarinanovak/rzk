package com.example.tournament_service.repository;


import com.example.tournament_service.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findAllByOrganizerId(Long organizerId);

    Optional<Tournament> findByIdAndOrganizerId(Long id, Long organizerId);
    Optional<Tournament> findById(Long id);
}
