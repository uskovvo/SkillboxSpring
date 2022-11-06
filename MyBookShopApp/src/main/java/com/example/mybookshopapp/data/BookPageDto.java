package com.example.mybookshopapp.data;

import java.util.List;

public class BookPageDto {

    private long count;
    private List<Book> books;

    public BookPageDto(List<Book> pageOfBook){
        this.books = pageOfBook;
        this.count = books.size();
    }
//    public BookPageDto(List<Book> pageOfRecommendedBooks, long count) {
//        this.books = pageOfRecommendedBooks;
//        this.count = count;
//    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
