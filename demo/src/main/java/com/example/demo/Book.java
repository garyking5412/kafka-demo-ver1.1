package com.example.demo;


import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book(Long id, String title,int price,Author author){
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
    }
    public Book(){

    }

    public String getTitle() {
        return title;
    }
}
