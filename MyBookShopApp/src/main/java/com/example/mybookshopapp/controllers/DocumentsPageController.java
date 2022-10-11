package com.example.mybookshopapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentsPageController {

    @GetMapping("/documents/slug")
    public String getDocumentsPage(){
        return "documents/slug";
    }

    @GetMapping("/documents")
    public String getDocuments(){
        return "documents/index";
    }
}
