package com.example.mybookshopapp.services;

import com.example.mybookshopapp.data.book.Book;
import com.example.mybookshopapp.data.book.links.Book2UserEntity;
import com.example.mybookshopapp.data.book.links.Book2UserTypeEntity;
import com.example.mybookshopapp.repositories.AuthorRepository;
import com.example.mybookshopapp.repositories.Book2UserEntityRepository;
import com.example.mybookshopapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final Book2UserEntityRepository b2uRepository;

    private final String KEPT = "1";
    private final String CART = "2";
    private final String PAID = "3";

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       Book2UserEntityRepository b2uRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.b2uRepository = b2uRepository;
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
            fromD = new SimpleDateFormat("yyyy/MM/dd").parse(from);
            toD = new SimpleDateFormat("yyyy/MM/dd").parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return bookRepository.findBookByPubDateBetween(fromD, toD, nextPage);
    }

    public List<Book> getRecentBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by("pubDate").descending());

        return bookRepository.findAll(nextPage).getContent();
    }

    private double getBookPopularity(Book book) {
        double kept = 0.0;
        double cart = 0.0;
        double paid = 0.0;

        List<Book2UserEntity> b2u = b2uRepository.findBook2UserEntityByBookId(Math.toIntExact(book.getId()));
        for (Book2UserEntity b : b2u) {
            Book2UserTypeEntity b2uType = b.getBook2UserType();
            switch (b2uType.getCode()) {
                case (KEPT):
                    kept++;
                case (CART):
                    cart++;
                case (PAID):
                    paid++;
            }
        }

        return paid + 0.7 * cart + 0.4 * kept;
    }



    public List<Book> getPageOfPopularBook(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByPopularity(nextPage);
//        Map<Book, Double> sortedPopularBook = new HashMap<>();
//        bookRepository.findAll(nextPage).forEach(b -> {
//            double r = getBookPopularity(b);
//            sortedPopularBook.put(b, r);
//        });
//        List<Book> sortPopularBook = new ArrayList<>();
//
//        sortedPopularBook
//                .entrySet()
//                .stream()
//                .sorted(
//                        Map
//                                .Entry
//                                .<Book, Double>comparingByValue()
//                                .reversed())
//                .forEach(
//                        bookDoubleEntry -> {
//                            sortPopularBook.add(bookDoubleEntry.getKey());
//                        });
//
//        System.out.println(sortPopularBook.size());
//
//        sortPopularBook.forEach(s -> {
//            System.out.println(s.getTitle());
//        });

//        return sortPopularBook;
    }
}