package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.book.links.Book2AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Book2AuthorEntityRepository extends JpaRepository<Book2AuthorEntity, Long> {
}