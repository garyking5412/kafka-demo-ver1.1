package com.example.kafkaconsumer.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MessageDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "to_email")
    private String to;
    private String toName;
    private String content;
//    public void setTo(String to1){
//        this.to = to1;
//    }
//
//    public String getTo() {
//        return this.to;
//    }
//
//    public String getToName() {
//        return this.toName;
//    }
//
//    public String getContent() {
//        return this.content;
//    }
//
//    public MessageDTO(){
//
//    }
//
//    public void setToName(String name) {
//        this.toName = name;
//    }
//
//    public void setContent(String content1) {
//        this.content = content1;
//    }
}
