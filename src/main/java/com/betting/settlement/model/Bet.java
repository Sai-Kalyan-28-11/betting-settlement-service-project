package com.betting.settlement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "bets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long betId;

    private Long userId;
    private Long eventId;
    private Long eventMarketId;
    private Long eventWinnerId;
    private BigDecimal betAmount;

    @Column(nullable = false)
    private Boolean settled = false;
}