package com.boardgame.contractservice.kafkaconfig;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OnMessageSendThenReceive {

    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final BlockingQueue<String> responseQueue = new ArrayBlockingQueue<>(1);

    @KafkaListener(id = "authservice-contractservice-listen", topics = "authservice-contractservice")
    public void listenAuthContractResponse(String in) {
        responseQueue.offer(in);
        System.out.println("Contractservice receive: " + in);
    }

    public Object getLessorData(Object dataSent) throws InterruptedException {
        kafkaTemplate.send("contractservice-authservice", dataSent);
        System.out.println("Contractservice sent: " + dataSent);
        return responseQueue.take();
    }

    @KafkaListener(id = "userservice-contractservice-listen", topics = "userservice-contractservice")
    public void listenUserContractResponse(String in) {
        responseQueue.offer(in);
        System.out.println("Contractservice receive: " + in);
    }

    public Object getLesseeData(Object dataSent) throws InterruptedException {
        kafkaTemplate.send("contractservice-userservice", dataSent);
        System.out.println("Contractservice sent: " + dataSent);
        return responseQueue.take();
    }

    @KafkaListener(id = "boardgameservice-contractservice-listen", topics = "boardgameservice-contractservice")
    public void listenBoardgameContractResponse(String in) {
        responseQueue.offer(in);
        System.out.println("Contractservice receive: " + in);
    }

    public Object getBoardgameData(Object dataSent) throws InterruptedException {
        kafkaTemplate.send("contractservice-boardgameservice", dataSent);
        System.out.println("Contractservice sent: " + dataSent);
        return responseQueue.take();
    }
}
