package com.example.kafkaconsumer;

import com.example.kafkaconsumer.model.MessageDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.backoff.FixedBackOff;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Properties;

@SpringBootApplication
@EnableAsync
public class KafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    @Bean
    JsonMessageConverter converter(){
        return new JsonMessageConverter();
    };

    @Bean
    JavaMailSender javaMailSender(){
        return new JavaMailSenderImpl();
    }

    @Bean
    SpringTemplateEngine springTemplateEngine(){
        return new SpringTemplateEngine();
    }

    @Bean
    DefaultErrorHandler errorHandler(KafkaOperations<String,Object> template){
        return new DefaultErrorHandler(new DeadLetterPublishingRecoverer(template),new FixedBackOff(1000L,2));
    }
}
