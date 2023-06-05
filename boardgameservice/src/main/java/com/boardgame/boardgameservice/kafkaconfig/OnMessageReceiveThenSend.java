package com.boardgame.boardgameservice.kafkaconfig;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.boardgame.boardgameservice.models.Boardgame;
import com.boardgame.boardgameservice.repositories.BoardgameRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OnMessageReceiveThenSend {
    @Autowired
    private final BoardgameRepository boardgameRepository;

    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // listen when contract service request for boardgame data
    @KafkaListener(id = "contractservice-boardgameservice-listen", topics = "contractservice-boardgameservice")
    public void listen(String in) {
        System.out.println("Boardgameservice receive: " + in);

        Boardgame boardgame = boardgameRepository.findById(UUID.fromString(in.replaceAll("^\"|\"$", ""))).orElseThrow();

        kafkaTemplate.send("boardgameservice-contractservice", boardgame);
        System.out.println("Boardgameservice sent: " + boardgame);
    }
}
