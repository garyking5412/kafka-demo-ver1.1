package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class StatService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatRepository statRepository;


//    @RetryableTopic(attempts = "3",dltTopicSuffix = "-dlt",backoff = @Backoff(delay = 2_000,multiplier = 3))
    @KafkaListener(id="TXTHAIstatService",topics = "stat")
    public void listen(StatDTO statDTO){
        logger.info("received: "+statDTO.getMessage());
        statRepository.save(statDTO);
    }
//    @KafkaListener(id="dltTXTHAIGroup",topics = "stat-dlt")
//    public void dltRetryableGroupListener(StatDTO statDTO){
//        logger.info("received on Statistic TXTHAI DLT Topics: " + statDTO.getMessage());
//    }
}
