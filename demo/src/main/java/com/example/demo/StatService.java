package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StatService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatRepository statRepository;

    @KafkaListener(id="statService",topics = "stat")
    public void listen(StatDTO statDTO){
        logger.info("received: "+statDTO.getMessage());
        statRepository.save(statDTO);
    }
}
