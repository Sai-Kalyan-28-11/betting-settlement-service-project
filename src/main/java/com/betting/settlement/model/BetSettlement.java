package com.betting.settlement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetSettlement {
    private Long betId;
    private Long userId;
    private Long eventId;
    private Long eventWinnerId;
    private Long betEventWinnerId;
    private BigDecimal betAmount;
    private Boolean isWinner;
    private BigDecimal payout;
}