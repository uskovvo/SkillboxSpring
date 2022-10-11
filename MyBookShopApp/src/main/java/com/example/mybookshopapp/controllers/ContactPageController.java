package com.example.mybookshopapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactPageController {

    @GetMapping("/contacts")
    public String getDocuments(){
        return "contacts";
    }
}
