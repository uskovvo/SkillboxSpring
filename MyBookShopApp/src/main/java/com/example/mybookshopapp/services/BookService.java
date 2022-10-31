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

    public int getCount(String title){
        List<Book> books = bookRepository.findBookByTitleContaining(title);
        return books.size();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }

    public Page<Book> getPageOfRecentBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by("pubDate").descending());
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfRecentBooksByDate(Date from, Date to, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by("pubDate").descending());
        return bookRepository.findBookByPubDate(from, to, nextPage);
    }
}