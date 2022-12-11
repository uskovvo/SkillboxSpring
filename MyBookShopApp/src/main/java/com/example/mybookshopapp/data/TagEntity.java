package com.example.mybookshopapp.data;

import com.example.mybookshopapp.data.book.Book;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    @Getter
    @Setter
    private String name;

    @ManyToMany
    @JoinTable(
            name = "book2tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @Getter
    @Setter
    private List<Book> bookList;
}
