package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class StatDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private Date createdDate;
    private boolean status;
//    public StatDTO(String s, Date date) {
//        this.message=s;
//        this.createdDate=date;
//    }
//
//    public void setMessage(String mess){
//        this.message = mess;
//    }
//
//    public void setCreatedDate(Date date){
//        this.createdDate = date;
//    }
//
//    public StatDTO() {
//
//    }
}
