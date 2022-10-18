package com.example.mybookshopapp.data.book.links;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book2user")
public class Book2UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Book2UserTypeEntity book2UserType;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book bookId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Book2UserTypeEntity getBook2UserType() {
        return book2UserType;
    }

    public void setBook2UserType(Book2UserTypeEntity book2UserType) {
        this.book2UserType = book2UserType;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public Book getBookId() {
        return bookId;
    }
}
