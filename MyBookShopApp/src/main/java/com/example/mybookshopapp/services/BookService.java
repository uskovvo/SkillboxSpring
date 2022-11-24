package com.example.mybookshopapp.services;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.book.links.Book2AuthorEntity;
import com.example.mybookshopapp.repositories.AuthorRepository;
import com.example.mybookshopapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public List<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }

    public List<Book> getPageOfRecentBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by("pubDate").descending());
        return bookRepository.findAllBooks(nextPage);
    }

    public List<Book> getPageOfRecentBooksByDate(String from, String to, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by("pubDate").descending());
        Date fromD = new Date();
        Date toD = new Date();
        try {
            fromD = new SimpleDateFormat("dd.MM.yyyy").parse(from);
            toD = new SimpleDateFormat("dd.MM.yyyy").parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return bookRepository.findBookByPubDateBetween(fromD, toD, nextPage);
    }
}