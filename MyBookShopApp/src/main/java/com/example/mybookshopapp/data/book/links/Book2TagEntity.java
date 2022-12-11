package com.example.mybookshopapp.data.book.links;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book2tag")
public class Book2TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "book_id", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private Integer bookId;

    @Column(name = "tag_id", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private Integer tagId;
}
