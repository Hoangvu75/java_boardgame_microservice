package com.boardgame.contractservice.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Lessor lessor;

    @Embedded
    private Lessee lessee;

    @ElementCollection
    @CollectionTable(name = "contract_boardgames", joinColumns = @JoinColumn(name = "contract_id"))
    @Builder.Default
    private List<Boardgame> boardgames = new ArrayList<>();
    

    private Timestamp startAt;
    private Timestamp endAt;

    private Timestamp createAt;
    private Timestamp updateAt;
}
