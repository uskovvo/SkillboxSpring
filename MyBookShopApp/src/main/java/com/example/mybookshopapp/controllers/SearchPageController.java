package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class SearchPageController {

    private final BookService bookService;

    @Autowired
    public SearchPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchData")
    public List<Book> getSearchObject(){
        return bookService.getBooksData();
    }

    @GetMapping("/search")
    public String getSearch(){
        return "search/index";
    }
}
