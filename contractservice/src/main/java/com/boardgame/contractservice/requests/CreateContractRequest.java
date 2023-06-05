package com.boardgame.contractservice.requests;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateContractRequest {

    @NotNull(message = "Lessee id is null")
    private UUID lesseeId;

    private UUID[] boardgames;

    @NotNull(message = "Start time is null")
    private Timestamp startAt;

    @NotNull(message = "End time is null")
    private Timestamp endAt;
}
