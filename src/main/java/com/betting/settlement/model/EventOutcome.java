package com.betting.settlement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventOutcome {
    private Long eventId;
    private String eventName;
    private Long eventWinnerId;
}