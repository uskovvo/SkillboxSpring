package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PopularBookPageController {

    private final BookService bookService;

    @Autowired
    public PopularBookPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("popularBook")
    public List<Book> bookList(){
        return bookService.getBooksData();
    }

    @GetMapping("/books/popular")
    public String popularBookPage(){
        return "books/popular";
    }
}
