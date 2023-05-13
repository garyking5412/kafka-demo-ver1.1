package com.example.kafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import java.util.Date;

import lombok.Data;

@Data
public class StatDTO {
    private int id;
    private String message;
    private Date createdDate;
    private boolean status;

    public StatDTO(String s, Date date) {
        this.message=s;
        this.createdDate=date;
    }

    public void setMessage(String mess){
        this.message = mess;
    }

    public void setCreatedDate(Date date){
        this.createdDate = date;
    }

    public StatDTO() {

    }
}
