package com.example.mybookshopapp.data.book.review;

import com.example.mybookshopapp.data.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "book_review")
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", columnDefinition = "INT", nullable = false)
    private Integer bookId;

    @Column(name = "user_id", columnDefinition = "INT", nullable = false)
    private Integer userId;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDateTime time;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @ManyToMany
    @JoinTable(
            name = "book_review_like",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> bookReviewLike;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public List<UserEntity> getBookReviewLike() {
        return bookReviewLike;
    }

    public void setBookReviewLike(List<UserEntity> bookReviewLike) {
        this.bookReviewLike = bookReviewLike;
    }
}
