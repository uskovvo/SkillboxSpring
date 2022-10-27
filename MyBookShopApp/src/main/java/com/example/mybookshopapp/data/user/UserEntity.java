package com.example.mybookshopapp.data.user;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.book.review.BookReviewEntity;
import com.example.mybookshopapp.data.book.review.MessageEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String hash;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT", nullable = false)
    private int balance;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "book2user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @ManyToMany
    @JoinTable(
            name = "file_download",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> fileDownload;

    @ManyToMany
    @JoinTable(
            name = "balance_transaction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> balanceTransaction;

    @ManyToMany
    @JoinTable(
            name = "book_review",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> bookReview;

    @ManyToMany
    @JoinTable(
            name = "book_review_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<BookReviewEntity> bookReviewLike;

    @OneToMany(mappedBy = "userId")
    private List<MessageEntity> messageList = new ArrayList<>();

    @OneToOne(mappedBy = "userId")
    private UserContactEntity userContactEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(List<Book> fileDownload) {
        this.fileDownload = fileDownload;
    }

    public List<Book> getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(List<Book> balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }

    public List<Book> getBookReview() {
        return bookReview;
    }

    public void setBookReview(List<Book> bookReview) {
        this.bookReview = bookReview;
    }

    public List<BookReviewEntity> getBookReviewLike() {
        return bookReviewLike;
    }

    public void setBookReviewLike(List<BookReviewEntity> bookReviewLike) {
        this.bookReviewLike = bookReviewLike;
    }

    public List<MessageEntity> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageEntity> messageList) {
        this.messageList = messageList;
    }

    public UserContactEntity getUserContactEntity() {
        return userContactEntity;
    }

    public void setUserContactEntity(UserContactEntity userContactEntity) {
        this.userContactEntity = userContactEntity;
    }
}
