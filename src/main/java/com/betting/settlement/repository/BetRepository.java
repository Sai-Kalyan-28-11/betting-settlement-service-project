package com.betting.settlement.repository;

import com.betting.settlement.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByEventIdAndSettledFalse(Long eventId);
}