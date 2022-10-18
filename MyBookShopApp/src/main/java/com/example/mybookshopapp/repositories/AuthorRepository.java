package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
