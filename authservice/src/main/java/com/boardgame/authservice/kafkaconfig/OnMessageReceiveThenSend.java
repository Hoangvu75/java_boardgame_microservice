package com.boardgame.authservice.kafkaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import com.boardgame.authservice.auth.config.JwtService;
import com.boardgame.authservice.auth.models.Manager;
import com.boardgame.authservice.auth.repositories.ManagerRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OnMessageReceiveThenSend {
    @Autowired
    private final ManagerRepository managerRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // listen when contract service request for manager data
    @KafkaListener(id = "contractservice-authservice-listen", topics = "contractservice-authservice")
    public void listen(String in) {
        System.out.println("Authservice receive: " + in);

        String username = jwtService.extractUsername(in.substring("Bearer ".length()));
        Manager manager = managerRepository.findByEmail(username).orElseThrow();

        kafkaTemplate.send("authservice-contractservice", manager);
        System.out.println("Authservice sent: " + manager);
    }
}
