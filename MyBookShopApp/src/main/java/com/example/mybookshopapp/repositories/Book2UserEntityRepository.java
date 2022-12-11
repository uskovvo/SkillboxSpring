package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.book.links.Book2UserEntity;
import com.example.mybookshopapp.data.book.links.Book2UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Book2UserEntityRepository extends JpaRepository<Book2UserEntity, Long> {

    @Query(value = "SELECT * FROM book2user WHERE type_id = 1", nativeQuery = true)
    List<Book2UserEntity> getBook2UserEntityKept();

    @Query("select count(b) from Book2UserEntity b where b.bookId = ?1 and b.book2UserType.id = ?2")
    int countBook2UserEntitiesByBookIdAndBook2UserType_Id(Long bookId, Long book2UserType_id);

    List<Book2UserEntity> findBook2UserEntityByBookId(Integer bookId);

    @Query(value = "SELECT * FROM book2user WHERE type_id = 2", nativeQuery = true)
    List<Book2UserEntity> getBook2UserEntityCart();

    @Query(value = "SELECT * FROM book2user WHERE type_id = 3", nativeQuery = true)
    List<Book2UserEntity> getBook2UserEntityPaid();
}