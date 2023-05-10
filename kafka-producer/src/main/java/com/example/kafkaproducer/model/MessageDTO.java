package com.example.kafkaproducer.model;

import lombok.Data;

@Data
public class MessageDTO {
    private int id;
    private String to;
    private String toName;
    private String content;
    public void setTo(String to1){
        this.to = to1;
    }

    public String getTo() {
        return this.to;
    }

    public String getToName() {
        return this.toName;
    }

    public String getContent() {
        return this.content;
    }

    public MessageDTO(){

    }

    public void setToName(String name) {
        this.toName = name;
    }

    public void setContent(String content1) {
        this.content = content1;
    }
}
