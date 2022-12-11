package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Dto.BookPageDto;
import com.example.mybookshopapp.data.Dto.SearchWordDto;
import com.example.mybookshopapp.data.book.Book;
import com.example.mybookshopapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> getRecentBooks() {
        return bookService.getRecentBooks(0, 6);
    }

    @ModelAttribute("popularBooks")
    public List<Book> bookList() {
        return bookService.getPageOfPopularBook(0, 6);
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping(value = {"/search", "/search/{searchWordMain}"})
    public String getSearchResults(@PathVariable(value = "searchWordMain", required = false)
                                           SearchWordDto searchWordDto, Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService
                        .getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5));
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWordMain}")
    @ResponseBody
    public BookPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit,
                                         @PathVariable(value = "searchWordMain", required = false) SearchWordDto searchWordDto) {

        return new BookPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit));
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BookPageDto getBooksPage(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        return new BookPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping(value = "/books/recentMain", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BookPageDto getRecentBook(@RequestParam(value = "from", required = false) String from,
                                     @RequestParam(value = "to", required = false) String to,
                                     @RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        return new BookPageDto(bookService.getPageOfRecentBooksByDate(from, to, offset, limit));
    }
}
