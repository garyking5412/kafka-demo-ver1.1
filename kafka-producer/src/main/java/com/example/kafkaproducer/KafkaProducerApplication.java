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
        return new NewTopic("noti",2,(short) 3);
    }

    @Bean
    NewTopic stat(){
        return new NewTopic("stat",2,(short) 3);
    }

}
