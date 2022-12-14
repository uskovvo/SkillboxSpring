package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.exceptions.BookShelfUploadFileException;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
        if (bookService.removeBookByRegex(regex.getRegex()) && !bindingResult.hasErrors()) {
            return "redirect:/books/shelf";
        } else {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            logger.info("Book is not found or request is empty");
            throw new BookShelfRemoveException("Book is not found or request is empty");
        }
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) throws Exception {
        if(file.isEmpty()){
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            logger.info("File is null");
            throw new BookShelfUploadFileException("File cannot be empty");
        }
        else {
            String name = file.getOriginalFilename();
            byte[] bytes = file.getBytes();

            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "external_uploads");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            logger.info("new file saved at: " + serverFile.getAbsolutePath());

            return "redirect:/books/shelf";
        }
    }

    @ExceptionHandler(BookShelfRemoveException.class)
    public String handlerError(Model model, BookShelfRemoveException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/500";
    }

    @ExceptionHandler(BookShelfUploadFileException.class)
    public String uploadHandlerError(Model model, BookShelfUploadFileException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/405";
    }
}
