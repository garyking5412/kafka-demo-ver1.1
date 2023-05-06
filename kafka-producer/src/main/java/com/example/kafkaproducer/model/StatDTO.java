package com.example.kafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class StatDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private Date createdDate;
    private Boolean status;

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
