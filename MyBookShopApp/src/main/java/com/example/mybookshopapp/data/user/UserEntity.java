package com.example.mybookshopapp.data.user;

import com.example.mybookshopapp.data.book.file.FileDownloadEntity;
import com.example.mybookshopapp.data.book.links.Book2UserEntity;
import com.example.mybookshopapp.data.book.review.BookReviewEntity;
import com.example.mybookshopapp.data.book.review.BookReviewLikeEntity;
import com.example.mybookshopapp.data.book.review.MessageEntity;
import com.example.mybookshopapp.data.payments.BalanceTransactionEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT NOT NULL")
    private int balance;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "userId")
    private List<Book2UserEntity> book2User = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<FileDownloadEntity> fileDownload = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<BalanceTransactionEntity> balanceTransaction = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<BookReviewEntity> bookReview = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<BookReviewLikeEntity> bookReviewLike = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<MessageEntity> messageList = new ArrayList<>();

    @OneToOne(mappedBy = "userId")
    private UserContactEntity userContactEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Book2UserEntity> getBook2User() {
        return book2User;
    }

    public void setBook2User(List<Book2UserEntity> book2User) {
        this.book2User = book2User;
    }

    public List<FileDownloadEntity> getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(List<FileDownloadEntity> fileDownload) {
        this.fileDownload = fileDownload;
    }

    public List<BalanceTransactionEntity> getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(List<BalanceTransactionEntity> balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }

    public List<BookReviewEntity> getBookReview() {
        return bookReview;
    }

    public void setBookReview(List<BookReviewEntity> bookReview) {
        this.bookReview = bookReview;
    }

    public List<BookReviewLikeEntity> getBookReviewLike() {
        return bookReviewLike;
    }

    public void setBookReviewLike(List<BookReviewLikeEntity> bookReviewLike) {
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
