package com.example.kafkaproducer.model;

import lombok.Data;

@Data
public class AccountDTO {
    private String name;
    private String email;

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
}
