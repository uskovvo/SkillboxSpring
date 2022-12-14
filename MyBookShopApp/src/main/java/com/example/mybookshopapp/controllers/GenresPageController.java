package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Dto.BookPageDto;
import com.example.mybookshopapp.data.Dto.SearchWordDto;
import com.example.mybookshopapp.data.book.Book;
import com.example.mybookshopapp.services.BookService;
import com.example.mybookshopapp.services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GenresPageController {

    private final GenresService genresService;
    private final BookService bookService;

    @Autowired
    public GenresPageController(GenresService genresService, BookService bookService) {
        this.genresService = genresService;
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

    @ModelAttribute("genresBooks")
    public List<Book> bookList(){
        return null;
    }

    @GetMapping("/genres/slug")
    public String genreBookPage(){
        return "genres/slug";
    }

    @GetMapping("/genres")
    public String genresPage(){
        return "genres/index";
    }
}
