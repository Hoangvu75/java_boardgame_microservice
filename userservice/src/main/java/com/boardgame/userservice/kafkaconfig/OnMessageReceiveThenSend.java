package com.boardgame.userservice.kafkaconfig;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.boardgame.userservice.models.User;
import com.boardgame.userservice.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OnMessageReceiveThenSend {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // listen when contract service request for user data
    @KafkaListener(id = "contractservice-userservice-listen", topics = "contractservice-userservice")
    public void listen(String in) {
        System.out.println("Userservice receive: " + in);

        User user = userRepository.findById(UUID.fromString(in.replaceAll("^\"|\"$", ""))).orElseThrow();

        kafkaTemplate.send("userservice-contractservice", user);
        System.out.println("Userservice sent: " + user);
    }
}
