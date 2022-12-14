package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.book.Book;
import com.example.mybookshopapp.data.Dto.BookPageDto;
import com.example.mybookshopapp.data.Dto.SearchWordDto;
import com.example.mybookshopapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PopularBookPageController {

    private final BookService bookService;

    @Autowired
    public PopularBookPageController(BookService bookService) {
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

    @ModelAttribute("popularBooks")
    public List<Book> bookList(){
        return bookService.getPageOfPopularBook(0, 5);
    }

    @GetMapping("/books/popular")
    public String popularBookPage(){
        return "books/popular";
    }

    @GetMapping(value = "/books/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BookPageDto getPopularityBooks(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit){
        return new BookPageDto(bookService.getPageOfPopularBook(offset, limit));
    }
}
