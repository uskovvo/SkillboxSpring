package com.example.mybookshopapp.data.book.review;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_review")
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book bookId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;

    @OneToMany(mappedBy = "bookReview")
    private List<BookReviewLikeEntity> bookReviewLike = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<BookReviewLikeEntity> getBookReviewLike() {
        return bookReviewLike;
    }

    public void setBookReviewLike(List<BookReviewLikeEntity> bookReviewLike) {
        this.bookReviewLike = bookReviewLike;
    }
}
