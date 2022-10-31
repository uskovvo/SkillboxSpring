package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.BookPageDto;
import com.example.mybookshopapp.data.SearchWordDto;
import com.example.mybookshopapp.services.BookService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults(){
        return new ArrayList<>();
    }

    @ModelAttribute("recentBooks")
    public List<Book> getRecentBooks(){
        return bookService.getPageOfRecentBooks(0, 5).getContent();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BookPageDto getBooksPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit){
        return new BookPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model){
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService
                        .getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5));
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BookPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit,
                                         @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto){
        Page<Book> page = bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit);
        int count = bookService.getCount(searchWordDto.getExample());
        return new BookPageDto(page.getContent(), count);
    }

    @GetMapping("/books/recent")
    public String getRecent(Model model){
        model.addAttribute("recentBooks", bookService.getPageOfRecentBooks(0, 20));
        return "/books/recent";
    }

    @GetMapping("/books/recentList")
    @ResponseBody
    public BookPageDto getNextPageRecentBooks(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit){
        return new BookPageDto(bookService.getPageOfRecentBooks(offset, limit).getContent());
    }

//    @GetMapping("/books/recent")
//    @RequestBody
//    public BookPageDto getRecentBook(@RequestParam("from") String from,
//                                     @RequestParam("to") String to,
//                                     @RequestParam("offset") Integer offset,
//                                     @RequestParam("limit") Integer limit){
//        try {
//            Date dateFrom = new SimpleDateFormat("yyyy/MMM/dd").parse(from);
//            Date dateTo = new SimpleDateFormat("yyyy/MMM/dd").parse(to);
//
//            return new BookPageDto(bookService.getPageOfRecentBooksByDate(dateFrom, dateTo, offset, limit).getContent());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
