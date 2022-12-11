package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.book.Book;
import com.example.mybookshopapp.data.Dto.BookPageDto;
import com.example.mybookshopapp.data.Dto.SearchWordDto;
import com.example.mybookshopapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecentBookPageController {

    private final BookService bookService;

    @Autowired
    public RecentBookPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDt(){
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResult(){
        return new ArrayList<>();
    }

    @ModelAttribute("recentBooks")
    public List<Book> getRecentBooks(){
        return bookService.getPageOfRecentBooks(0, 5);
    }

    @GetMapping(value = {"/search", "/search/{searchWordRecent}"})
    public String getSearchResult(@PathVariable(value = "searchWordRecent", required = false)
                                           SearchWordDto searchWordDto, Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService
                        .getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5));
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWordRecent}")
    @ResponseBody
    public BookPageDto getNextSearchPag(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit,
                                         @PathVariable(value = "searchWordRecent", required = false) SearchWordDto searchWordDto) {

        return new BookPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit));
    }

    @GetMapping(value = "/books/recent", produces = MediaType.TEXT_HTML_VALUE)
    public String getRecent(Model model) {
        model
                .addAttribute(
                        "dateSearch",
                        bookService
                                .getPageOfRecentBooksByDate("2022/09/01","2022/10/01",0,5));
        return "/books/recent";
    }

    @GetMapping(value = "/books/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BookPageDto getRecentBook(@RequestParam(value = "from", required = false) String from,
                                     @RequestParam(value = "to", required = false) String to,
                                     @RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        return new BookPageDto(bookService.getPageOfRecentBooksByDate(from, to, offset, limit));
    }
}
