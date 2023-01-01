package com.example.mybookshopapp.data.book;

import com.example.mybookshopapp.data.Author;
import com.example.mybookshopapp.data.Genre;
import com.example.mybookshopapp.data.TagEntity;
import com.example.mybookshopapp.data.book.links.Book2UserEntity;
import com.example.mybookshopapp.data.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
@ApiModel(description = "entity representing a book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id generated by db automatically")
    private Long id;

    @Column(name = "pub_date", columnDefinition = "DATE", nullable = false)
    @ApiModelProperty("date of book publication")
    @JsonIgnore
    private Date pubDate;

    @Column(name = "is_bestseller", columnDefinition = "INT", nullable = false)
    @ApiModelProperty("if isBestseller = 1 so the book is considered to be bestseller and" +
            "if 0 the book is not bestseller")
    private Integer isBestseller;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty("mnemonic identity sequence of characters")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty("books title")
    private String title;

    @Column(columnDefinition = "VARCHAR(255)")
    @ApiModelProperty("image url")
    private String image;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty("book description text")
    @JsonIgnore
    private String description;

    @ManyToMany
    @JoinTable(
            name = "book2author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnore
    private List<Author> authors;

    @JsonGetter("authors")
    public String getAuthorsName(){
        String name = "";
        int size = authors.size();
        for(Author a: authors){
            if(size > 1){
                name = name + a.getName() + "\n";
            }else if (size == authors.size()) {
                name = name + a.getName();
            }
        }
        return name;
    }

    @Column(name = "price", columnDefinition = "INT", nullable = false)
    @JsonProperty("price")
    @ApiModelProperty("books price")
    private Integer priceOld;

    @Column(name = "discount", columnDefinition = "NUMERIC DEFAULT 0", nullable = false)
    @JsonProperty("discount")
    @ApiModelProperty("books discount")
    private double price;

    public Integer discount(){
        return Math.toIntExact(Math.round(price * 100));
    }

    @JsonProperty
    public Integer discountPrice(){
        if(price < 0.1){
            return priceOld;
        }
        return priceOld - Math.toIntExact(Math.round(price * priceOld));
    }

    @JsonProperty
    public Integer rating(){
        return 5;
    }

    @ManyToMany
    @JoinTable(
            name = "book2genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnore
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "book2user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<UserEntity> users;

    @JsonProperty
    public String status(){
        return "KEPT";
    }

    @ManyToMany
    @JoinTable(
            name = "file_download",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<UserEntity> fileDownload;

    @ManyToMany
    @JoinTable(
            name = "balance_transaction",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<UserEntity> balanceTransaction;

    @ManyToMany
    @JoinTable(
            name = "book_review",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<UserEntity> bookReview;

    @ManyToMany
    @JoinTable(
            name = "book2tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private List<TagEntity> tagEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0.1){
            this.price = 0;
        }else {
            this.price = price;
        }
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<UserEntity> getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(List<UserEntity> fileDownload) {
        this.fileDownload = fileDownload;
    }

    public List<UserEntity> getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(List<UserEntity> balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }

    public List<UserEntity> getBookReview() {
        return bookReview;
    }

    public void setBookReview(List<UserEntity> bookReview) {
        this.bookReview = bookReview;
    }

    public List<TagEntity> getTagEntityList() {
        return tagEntityList;
    }

    public void setTagEntityList(List<TagEntity> tagEntityList) {
        this.tagEntityList = tagEntityList;
    }
}
