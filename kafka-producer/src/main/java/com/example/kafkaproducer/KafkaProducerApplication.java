package com.example.kafkaproducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Bean
    NewTopic noti(){
        return new NewTopic("account",2,(short) 1);
    }

    @Bean
    NewTopic stat(){
        return new NewTopic("statistic",2,(short) 1);
    }

    @Bean
    NewTopic mess(){
        return new NewTopic("message",2,(short) 1);
    }

}
