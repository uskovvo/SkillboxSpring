package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.Author;
import com.example.mybookshopapp.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorById(Long id);

}
