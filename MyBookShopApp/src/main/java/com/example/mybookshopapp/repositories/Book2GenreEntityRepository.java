package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.book.links.Book2GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Book2GenreEntityRepository extends JpaRepository<Book2GenreEntity, Long> {
}