package com.example.kafkaconsumer.Service;


import com.example.kafkaconsumer.model.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;


@Service
@Slf4j
public class EmailService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendEmail(MessageDTO messageDTO) {
        try {
            logger.info("Start sending email......");
            Context context = new Context();
            context.setVariable("name", messageDTO.getToName());
            context.setVariable("content", messageDTO.getContent());
            String html = templateEngine.process("email-template", context);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo("thaitx512@qa.team");
            message.setSubject("subject none");
            message.setText(html);
            javaMailSender.send(message);
            logger.info("End sending email.........");
        } catch (Exception e) {
            logger.error("Email sent with error: " + e.getMessage());
        }
    }
}
