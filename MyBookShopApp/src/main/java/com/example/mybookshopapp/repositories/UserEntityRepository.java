package com.example.mybookshopapp.repositories;

import com.example.mybookshopapp.data.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}