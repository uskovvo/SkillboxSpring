package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("from Book where isBestseller=1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    List<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

    List<Book> findBookByPubDateBetween(Date from, Date to, Pageable nextPage);

    @Query("from Book")
    List<Book> findAllBooks(Pageable nextPage);
}
