package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.book.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query(value = "select b.*\n" +
            "from books as b\n" +
            "left join (select book_id, count(book_id) as bookCount\n" +
            "from book2user\n" +
            "where type_id = 1\n" +
            "group by book_id\n" +
            ") as keptBooks on keptBooks.book_id = b.id \n" +
            "left join (select book_id, count(book_id) as bookcount\n" +
            "from book2user\n" +
            "where type_id = 2\n" +
            "group by book_id\n" +
            ") as cartBooks on cartBooks.book_id = b.id\n" +
            "left join (select book_id, count(book_id) as bookcount\n" +
            "from book2user\n" +
            "where type_id = 3\n" +
            "group by book_id\n" +
            ") as paidBooks on paidBooks.book_id = b.id\n" +
            "order by\n" +
            "0.4 * (case when keptBooks.bookCount is null\n" +
            "then 0\n" +
            "else keptBooks.bookCount end) + 0.7 * (case when cartBooks.bookCount\n" +
            "is null\n" +
            "then 0\n" +
            "else cartBooks.bookCount end) + (case when paidBooks.bookCount is null\n" +
            "then 0\n" +
            "else paidBooks.bookCount end) desc", nativeQuery = true)
    List<Book> getBooksByPopularity(Pageable nextPage);
}
