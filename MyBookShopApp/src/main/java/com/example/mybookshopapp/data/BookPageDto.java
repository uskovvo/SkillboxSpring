package com.example.mybookshopapp.data;

import java.util.List;

public class BookPageDto {

    private Integer count;
    private List<Book> books;

    public BookPageDto(List<Book> pageOfBook){
        this.books = pageOfBook;
        this.count = books.size();
    }
    public BookPageDto(List<Book> pageOfRecommendedBooks, int count) {
        this.books = pageOfRecommendedBooks;
        this.count = count;
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
