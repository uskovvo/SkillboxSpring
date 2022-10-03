package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GenresPageController {

    private final GenresService genresService;

    @Autowired
    public GenresPageController(GenresService genresService) {
        this.genresService = genresService;
    }

    @GetMapping("genres/")
    public String genresPage(Model model){
        model.addAttribute("genreData", genresService.getGenresData());
        return "genres/index";
    }
}
