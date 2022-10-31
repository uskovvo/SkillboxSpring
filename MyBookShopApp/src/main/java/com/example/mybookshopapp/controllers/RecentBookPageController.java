package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Author;
import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.BookPageDto;
import com.example.mybookshopapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class RecentBookPageController {

    private final BookService bookService;

    @Autowired
    public RecentBookPageController(BookService bookService) {
        this.bookService = bookService;
    }


}
