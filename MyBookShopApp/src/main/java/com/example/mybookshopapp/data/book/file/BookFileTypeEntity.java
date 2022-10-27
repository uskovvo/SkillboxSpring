package com.example.mybookshopapp.data.book.file;

import com.example.mybookshopapp.data.BookFile;

import javax.persistence.*;

@Entity
@Table(name = "book_file_type")
public class BookFileTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(mappedBy = "bookFileTypeId")
    private BookFile bookFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookFile getBookFile() {
        return bookFile;
    }

    public void setBookFile(BookFile bookFile) {
        this.bookFile = bookFile;
    }
}
