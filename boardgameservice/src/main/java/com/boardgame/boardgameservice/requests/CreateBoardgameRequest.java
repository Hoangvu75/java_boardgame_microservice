package com.boardgame.boardgameservice.requests;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBoardgameRequest {
    
    @NotBlank(message = "Boardgame title cannot be blank")
    private String title;

    @NotBlank(message = "Boardgame description cannot be blank")
    private String description;

    @NotBlank(message = "Boardgame image url cannot be blank")
    private String imageUrl;

    @NotNull(message = "Boardgame player number must be defined")
    private int playerNumberMin;

    @NotNull(message = "Boardgame player number must be defined")
    private int playerNumberMax;

    @NotNull(message = "Boardgame duration must be defined")
    private double durationMin;

    @NotNull(message = "Boardgame duration must be defined")
    private double durationMax;

    @NotNull(message = "Boardgame age limit must be defined")
    private int ageLimit;

    @NotBlank(message = "Boardgame publisher cannot be blank")
    private String publisher;

    private Date releaseDate;

    @NotNull(message = "Boardgame price must be defined")
    private double price;
}
