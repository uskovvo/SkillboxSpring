package com.example.mybookshopapp.data;

import java.util.List;

public class BookPageDto {

    private Integer count;
    private List<Book> books;

    public BookPageDto(List<Book> pageOfRecommendedBooks) {
        this.books = pageOfRecommendedBooks;
        this.count = books.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
