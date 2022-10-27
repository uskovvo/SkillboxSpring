package com.example.mybookshopapp.data.book.review;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_review_like")
public class BookReviewLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_id", columnDefinition = "INT", nullable = false)
    private Integer bookReview;

    @Column(name = "user_id", columnDefinition = "INT", nullable = false)
    private Integer userId;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDateTime time;

    @Column(columnDefinition = "SMALLINT", nullable = false)
    private short value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBookReview() {
        return bookReview;
    }

    public void setBookReview(Integer bookReview) {
        this.bookReview = bookReview;
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

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
