package com.example.mybookshopapp.data;

import com.example.mybookshopapp.data.book.links.Book2AuthorEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author implements Comparable<Author>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String photo;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "authorId")
    private List<Book2AuthorEntity> book2AuthorEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Book2AuthorEntity> getBook2AuthorEntityList() {
        return book2AuthorEntityList;
    }

    public void setBook2AuthorEntityList(List<Book2AuthorEntity> book2AuthorEntityList) {
        this.book2AuthorEntityList = book2AuthorEntityList;
    }

    @Override
    public int compareTo(Author o) {
        return name.compareTo(o.name);
    }
}
