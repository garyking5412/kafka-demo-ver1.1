package com.example.kafkaconsumer.Service;
import com.example.kafkaconsumer.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailService emailService;

    @KafkaListener(id="console-consumer-19584",topics = "noti")
    public void listen(MessageDTO messageDTO){
        logger.info("received: " + messageDTO.getTo());
        emailService.sendEmail(messageDTO);
    }
}
