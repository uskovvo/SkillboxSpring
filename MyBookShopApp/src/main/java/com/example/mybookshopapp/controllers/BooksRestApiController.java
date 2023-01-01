package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.services.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(description = "book data api")
public class BooksRestApiController {

    private final BookService bookService;

    @Autowired
    public BooksRestApiController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping("/books/by-author")
//    @ApiOperation("operation to get books list of bookshop by passed author name")
//    public ResponseEntity<List<Book>> bookByAuthor(@RequestParam("author") String authorName){
//        return ResponseEntity.ok(bookService.getBooksByAuthor(authorName));
//    }

//    @GetMapping("/books/by-title")
//    @ApiOperation("operation to get books list of bookshop by books title")
//    public ResponseEntity<List<Book>> bookByTitle(@RequestParam("title") String title){
//        return ResponseEntity.ok(bookService.getBooksByTitle(title));
//    }

//    @GetMapping("/books/by-price-range")
//    @ApiOperation("operation to get books list of bookshop by price range")
//    public ResponseEntity<List<Book>> priceRangeBooks(@RequestParam("min") Integer min, @RequestParam("max") Integer max){
//         return ResponseEntity.ok(bookService.getBooksWithPriceBetween(min, max));
//    }

//    @GetMapping("/books/with-max-discount")
//    @ApiOperation("operation to get books list of bookshop by max discount")
//    public ResponseEntity<List<Book>> maxDiscountBooks(){
//        return ResponseEntity.ok(bookService.getBooksWithMaxDiscount());
//    }

//    @GetMapping("/books/bestsellers")
//    @ApiOperation("operation to get all bestsellers books")
//    public ResponseEntity<List<Book>> bestSellerBooks(){
//        return ResponseEntity.ok(bookService.getBestsellers());
//    }
}
