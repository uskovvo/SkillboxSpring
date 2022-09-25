package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.exceptions.BookShelfLoginException;
import org.example.app.services.BookService;
import org.example.app.exceptions.BookShelfRemoveException;
import org.example.web.dto.Book;
import org.example.web.dto.BookToRemoveByRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/books")
@Scope("singleton")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookToRemoveByRegex", new BookToRemoveByRegex());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
//            model.addAttribute("bookToRemoveByRegex", new BookToRemoveByRegex());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        } else {
            bookService.saveBook(book);
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeByRegex")
    public String removeBookByRegex(@Valid BookToRemoveByRegex regex, BindingResult bindingResult, Model model) throws BookShelfRemoveException {
        if (bookService.removeBookByRegex(regex.getRegex())) {
            return "redirect:/books/shelf";
        } else {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            logger.info("Book is not found or request is empty");
            throw new BookShelfRemoveException("Book is not found or request is empty");
        }
    }

    @ExceptionHandler(BookShelfRemoveException.class)
    public String handlerError(Model model, BookShelfRemoveException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/500";
    }
}
