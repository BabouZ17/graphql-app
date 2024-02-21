package com.example.games.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.games.model.Review;

public interface ReviewRepository extends JpaRepository<Review, String> {
}
