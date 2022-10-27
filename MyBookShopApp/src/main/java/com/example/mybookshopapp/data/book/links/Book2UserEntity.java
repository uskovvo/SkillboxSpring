package com.example.mybookshopapp.data.book.links;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book2user")
public class Book2UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Book2UserTypeEntity book2UserType;

    @Column(name = "user_id", columnDefinition = "INT", nullable = false)
    private Integer userId;

    @Column(name = "book_id", columnDefinition = "INT", nullable = false)
    private Integer bookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
