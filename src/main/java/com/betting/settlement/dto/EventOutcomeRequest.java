package com.betting.settlement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventOutcomeRequest {
    @NotNull(message = "Event ID is required")
    private Long eventId;

    @NotNull(message = "Event name is required")
    private String eventName;

    @NotNull(message = "Event winner ID is required")
    private Long eventWinnerId;
}