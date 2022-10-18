package com.example.mybookshopapp.data;

import com.example.mybookshopapp.data.book.file.FileDownloadEntity;
import com.example.mybookshopapp.data.book.links.Book2AuthorEntity;
import com.example.mybookshopapp.data.book.links.Book2GenreEntity;
import com.example.mybookshopapp.data.book.links.Book2UserEntity;
import com.example.mybookshopapp.data.book.review.BookReviewEntity;
import com.example.mybookshopapp.data.payments.BalanceTransactionEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pub_date", columnDefinition = "DATE NOT NULL")
    private Date pubDate;

    @Column(name = "is_bestseller", columnDefinition = "SMALLINT NOT NULL")
    private Integer isBestseller;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", columnDefinition = "INT", nullable = false)
    private Integer priceOld;

    @Column(name = "discount", columnDefinition = "SMALLINT DEFAULT 0", nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "bookId")
    private List<Book2AuthorEntity> book2Author;

    @OneToMany(mappedBy = "bookId")
    private List<Book2GenreEntity> book2Genre;

    @OneToMany(mappedBy = "bookId")
    private List<Book2UserEntity> book2User;

    @OneToMany(mappedBy = "bookId")
    private List<FileDownloadEntity> fileDownload = new ArrayList<>();

    @OneToMany(mappedBy = "bookId")
    private List<BalanceTransactionEntity> balanceTransaction = new ArrayList<>();

    @OneToMany(mappedBy = "bookId")
    private List<BookReviewEntity> bookReview = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book2AuthorEntity> getBook2Author() {
        return book2Author;
    }

    public void setBook2Author(List<Book2AuthorEntity> book2Author) {
        this.book2Author = book2Author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Integer isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Book2GenreEntity> getBook2Genre() {
        return book2Genre;
    }

    public void setBook2Genre(List<Book2GenreEntity> book2Genre) {
        this.book2Genre = book2Genre;
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
}
