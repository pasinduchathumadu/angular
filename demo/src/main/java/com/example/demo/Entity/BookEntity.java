package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Integer Id;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Integer price;
    @Column(name = "category")
    private String category;
    public BookEntity() {
    }
    public BookEntity(Integer Id , String bookName , String author , String category , Integer price){
        super();
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.price = price;
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }





    public String getBookName() {
        return bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }












}
