package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Dto.BookPageDto;
import com.example.mybookshopapp.data.Dto.SearchWordDto;
import com.example.mybookshopapp.data.book.Book;
import com.example.mybookshopapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartPageController {

    private final BookService bookService;

    @Autowired
    public CartPageController(BookService bookService) {
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

    @ModelAttribute("cartBooks")
    public List<Book> bookList(){
        return null;
    }

    @GetMapping("/cart")
    public String getCart(){
        return "cart";
    }

    @GetMapping(value = {"/search", "/search/{searchWordCart}"})
    public String getSearchResults(@PathVariable(value = "searchWordCart", required = false)
                                           SearchWordDto searchWordDto, Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService
                        .getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5));
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWordCart}")
    @ResponseBody
    public BookPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit,
                                         @PathVariable(value = "searchWordCart", required = false) SearchWordDto searchWordDto) {

        return new BookPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit));
    }
}
