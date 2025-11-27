package com.betting.settlement.service;

import com.betting.settlement.model.Bet;
import com.betting.settlement.model.BetSettlement;
import com.betting.settlement.model.EventOutcome;
import com.betting.settlement.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BetMatchingService {

    private final BetRepository betRepository;
    private final RocketMQProducerService rocketMQProducerService;

    private static final BigDecimal WINNING_MULTIPLIER = new BigDecimal("2.0");

    @Transactional
    public void matchAndSettleBets(EventOutcome eventOutcome) {
        log.info("Matching bets for eventId: {}", eventOutcome.getEventId());

        // Find all unsettled bets for this event
        List<Bet> betsToSettle = betRepository.findByEventIdAndSettledFalse(eventOutcome.getEventId());

        log.info("Found {} bets to settle for eventId: {}", betsToSettle.size(), eventOutcome.getEventId());

        for (Bet bet : betsToSettle) {
            BetSettlement settlement = createBetSettlement(bet, eventOutcome);

            // Send settlement to RocketMQ
            rocketMQProducerService.sendBetSettlement(settlement);

            // Mark bet as settled
            bet.setSettled(true);
            betRepository.save(bet);

            log.info("Bet marked as settled: betId={}, userId={}, isWinner={}",
                    bet.getBetId(), bet.getUserId(), settlement.getIsWinner());
        }
    }

    private BetSettlement createBetSettlement(Bet bet, EventOutcome eventOutcome) {
        boolean isWinner = bet.getEventWinnerId().equals(eventOutcome.getEventWinnerId());
        BigDecimal payout = isWinner ? bet.getBetAmount().multiply(WINNING_MULTIPLIER) : BigDecimal.ZERO;

        return new BetSettlement(
                bet.getBetId(),
                bet.getUserId(),
                bet.getEventId(),
                eventOutcome.getEventWinnerId(),
                bet.getEventWinnerId(),
                bet.getBetAmount(),
                isWinner,
                payout
        );
    }
}