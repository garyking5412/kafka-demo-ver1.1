package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.model.AccountDTO;
import com.example.kafkaproducer.model.MessageDTO;
import com.example.kafkaproducer.model.StatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value="/account")
public class AccountController {

    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    @PostMapping(path="/create")
    public AccountDTO create(@RequestBody AccountDTO account){
        StatDTO stat = new StatDTO();
        stat.setMessage("Account"+account.getEmail()+"is created");
        stat.setCreatedDate(new Date());
        //send noti
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(account.getEmail());
        messageDTO.setToName(account.getName());
        messageDTO.setContent("KAFKA BANKAI!");
        kafkaTemplate.send("noti",messageDTO);
        kafkaTemplate.send("stat",stat);
        return account;
    }
}
