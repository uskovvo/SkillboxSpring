package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.book.links.Book2UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Book2UserTypeEntityRepository extends JpaRepository<Book2UserTypeEntity, Long> {
}