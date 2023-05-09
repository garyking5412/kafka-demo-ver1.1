package com.example.kafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class StatDTO {
    private String message;
    private Date createdDate;

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
