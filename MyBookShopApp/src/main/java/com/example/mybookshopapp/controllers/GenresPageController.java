package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Author;
import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.Genre;
import com.example.mybookshopapp.services.BookService;
import com.example.mybookshopapp.services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class GenresPageController {

    private final GenresService genresService;
    private final BookService bookService;

    @Autowired
    public GenresPageController(GenresService genresService, BookService bookService) {
        this.genresService = genresService;
        this.bookService = bookService;
    }

    @ModelAttribute("genresBooks")
    public List<Book> bookList(){
        return bookService.getBooksData();
    }

//    @ModelAttribute("genres")
//    public List<Genre> genresList(){
//        return genresService.getGenresData();
//    }

    @GetMapping("/genres/slug")
    public String genreBookPage(){
        return "genres/slug";
    }

    @GetMapping("/genres")
    public String genresPage(){
        return "genres/index";
    }
}
