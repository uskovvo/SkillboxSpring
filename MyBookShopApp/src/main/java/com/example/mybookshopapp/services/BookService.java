package com.example.mybookshopapp.services;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        List<Book> books = bookRepository.findAll();
        return new ArrayList<>(books);
    }
}