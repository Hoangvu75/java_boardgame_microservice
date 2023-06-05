package com.boardgame.contractservice.models;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Lessor {
    private UUID lessorId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "lessor_firstName")),
            @AttributeOverride(name = "lastName", column = @Column(name = "lessor_lastName"))
    })
    private Name name;

    @Column(name = "lessor_phoneNumber")
    private String phoneNumber;

    public Lessor parseLessorFromJson(JsonNode jsonNode) {
        UUID lessorId = UUID.fromString(jsonNode.get("id").toString().replaceAll("^\"|\"$", ""));
        Name name = new Name(
                jsonNode.get("name").get("firstName").toString().replaceAll("^\"|\"$", ""),
                jsonNode.get("name").get("lastName").toString().replaceAll("^\"|\"$", ""));
        String phoneNumber = jsonNode.get("phoneNumber").toString().replaceAll("^\"|\"$", "");
        return new Lessor(lessorId, name, phoneNumber);
    }
}