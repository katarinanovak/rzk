package com.example.tournament_service.repository;


import com.example.tournament_service.model.TournamentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
;

@Repository
public interface TournamentTypeRepository extends JpaRepository<TournamentType, Long> {


}
