package com.boardgame.boardgameservice.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boardgames")
public class Boardgame {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    private String imageUrl;

    private int playerNumberMin;
    private int playerNumberMax;

    private double durationMin;
    private double durationMax;

    private int ageLimit;
    private String publisher;
    private double price;
    private Date releaseDate;

    private Timestamp createAt;
    private Timestamp updateAt;
}
