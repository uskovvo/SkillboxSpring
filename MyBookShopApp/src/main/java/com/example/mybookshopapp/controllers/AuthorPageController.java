package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Author;
import com.example.mybookshopapp.data.book.Book;
import com.example.mybookshopapp.data.Dto.BookPageDto;
import com.example.mybookshopapp.data.Dto.SearchWordDto;
import com.example.mybookshopapp.services.AuthorService;
import com.example.mybookshopapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class AuthorPageController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorPageController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults(){
        return new ArrayList<>();
    }

    @ModelAttribute("authorsMap")
    public Map<String, Set<Author>> authorsMap(){
        return authorService.getAuthorData();
    }

    @ModelAttribute("authorBooks")
    public List<Book> bookList(){
        return null;
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "/authors/index";
    }

    @GetMapping("/authors/slug")
    public String authorPage(){
        return "authors/slug";
    }
}
