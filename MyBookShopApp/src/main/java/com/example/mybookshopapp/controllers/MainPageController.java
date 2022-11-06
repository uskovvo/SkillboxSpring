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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                        .getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BookPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit,
                                         @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto){

        Page<Book> page = bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit);
        BookPageDto bookPageDto = new BookPageDto(page.getContent());
        bookPageDto.setCount(page.getTotalElements());
        return bookPageDto;
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

    @GetMapping("/books/recentDate")
    @ResponseBody
    public BookPageDto getRecentBook(@RequestParam(value = "from", required = false) String from,
                                     @RequestParam(value = "to", required = false) String to,
                                     @RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit){
        try {
            LocalDate now = LocalDate.now();
            LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
            if(from.isEmpty()){
                from = oneMonthAgo.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            }
            if(to.isEmpty()){
                to = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            }
            Date dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(from);
            Date dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(to);

            return new BookPageDto(bookService.getPageOfRecentBooksByDate(dateFrom, dateTo, offset, limit).getContent());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
