package com.example.demo;

import javax.persistence.*;
import java.util.List;


@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
    public Author(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public Author(){

    }
}
