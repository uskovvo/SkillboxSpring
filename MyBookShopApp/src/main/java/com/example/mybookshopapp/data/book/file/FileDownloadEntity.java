package com.example.mybookshopapp.data.book.file;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.book.links.Book2UserEntity;
import com.example.mybookshopapp.data.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "file_download")
public class FileDownloadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book bookId;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 1")
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
}
