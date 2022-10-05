package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Author;
import com.example.mybookshopapp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Set;

@Controller
public class AuthorPageController {

    private final AuthorService authorService;

    @Autowired
    public AuthorPageController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, Set<Author>> authorsMap(){
        return authorService.getAuthorData();
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "/authors/index";
    }
}
