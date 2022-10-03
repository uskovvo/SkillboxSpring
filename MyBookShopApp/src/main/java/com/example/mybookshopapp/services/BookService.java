package com.example.mybookshopapp.services;

import com.example.mybookshopapp.data.Author;
import com.example.mybookshopapp.data.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query(
                "SELECT * FROM books " +
                        "INNER JOIN book2author " +
                        "ON books.id = book2author.book_id " +
                        "INNER JOIN authors " +
                        "ON book2author.author_id = authors.id",
                (ResultSet rs, int rowNum) ->
                {
                    Book book = new Book();
                    Author author = new Author();
                    author.setLastName(rs.getString("last_name"));
                    author.setFirstName(rs.getString("first_name"));
                    book.setId(rs.getInt("id"));
                    book.setAuthor(author.getLastName() + " " + author.getFirstName());
                    book.setTitle(rs.getString("title"));
                    book.setPriceOld(rs.getString("priceOld"));
                    book.setPrice(rs.getString("price"));
                    return book;
                });
        return new ArrayList<>(books);
    }
}