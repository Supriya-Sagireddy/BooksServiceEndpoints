package org.example.model;



import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;


@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String publisher;
    private String author;

    public Book(String name, String publisher, String author) {
        this.name = name;
        this.publisher = publisher;
        this.author=author;
    }
    public Book(){}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }


}
