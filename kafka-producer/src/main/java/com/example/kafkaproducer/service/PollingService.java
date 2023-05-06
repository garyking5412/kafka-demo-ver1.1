package com.example.kafkaproducer.service;


import com.example.kafkaproducer.model.MessageDTO;
import com.example.kafkaproducer.model.StatDTO;
import com.example.kafkaproducer.repository.MessageRepository;
import com.example.kafkaproducer.repository.StatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PollingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    StatRepository statRepository;

    @Scheduled(fixedDelay = 1000)
    public void produce() {
        List<MessageDTO> messageDTOS = messageRepository.findByStatus(false);
        List<StatDTO> statDTOS = statRepository.findByStatus(false);
        List<MessageDTO> updatedMessages = new ArrayList<>();
        List<StatDTO> updatedStats = new ArrayList<>();
        for (MessageDTO messageDTO : messageDTOS
        ) {
            try {
                CompletableFuture<SendResult<String, Object>> result = kafkaTemplate.send("noti", messageDTO);
                System.out.println("succeeded transfer on message offset: " + result.get().getRecordMetadata().offset() + " on partition: " + result.get().getRecordMetadata().partition());
                logger.info("updating message status...........");
                messageDTO.setStatus(true);
                updatedMessages.add(messageDTO);
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                continue;
            }
        }
        for (StatDTO statDTO : statDTOS
        ) {
            try {
                CompletableFuture<SendResult<String, Object>> result = kafkaTemplate.send("stat", statDTO);
                System.out.println("succeeded transfer on statistic offset: " + result.get().getRecordMetadata().offset() + " on partition: " + result.get().getRecordMetadata().partition());
                logger.info("updating statistic status...........");
                statDTO.setStatus(true);
                updatedStats.add(statDTO);
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                continue;
            }
        }
        if (updatedMessages.size() > 0) {
            logger.info("saving message status...........");
            messageRepository.saveAll(updatedMessages);
        }
        if (updatedStats.size() > 0) {
            logger.info("saving statistic status...........");
            statRepository.saveAll(updatedStats);
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void flushDatabase(){
        logger.info("flushing database...........");
        List<MessageDTO> messageDTOS = messageRepository.findByStatus(true);
        List<StatDTO> statDTOS = statRepository.findByStatus(true);
        messageRepository.deleteAll(messageDTOS);
        statRepository.deleteAll(statDTOS);
    }
}
