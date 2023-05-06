package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.model.AccountDTO;
import com.example.kafkaproducer.model.MessageDTO;
import com.example.kafkaproducer.model.StatDTO;
import com.example.kafkaproducer.repository.MessageRepository;
import com.example.kafkaproducer.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RestController
@EnableAsync
@RequestMapping(value="/account")
public class AccountController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    StatRepository statRepository;

    @PostMapping(path = "/create")
    public AccountDTO create(@RequestBody AccountDTO account) {
        StatDTO stat = new StatDTO();
        stat.setMessage("Account" + account.getEmail() + "is created");
        stat.setCreatedDate(new Date());
        stat.setStatus(false);
        //send noti
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(account.getEmail());
        messageDTO.setToName(account.getName());
        messageDTO.setContent("KAFKA BANKAI!");
        messageDTO.setStatus(false);
        messageRepository.save(messageDTO);
        statRepository.save(stat);
        return account;
    }
}
