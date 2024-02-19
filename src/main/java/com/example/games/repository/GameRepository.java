package com.example.games.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.games.model.Game;

public interface GameRepository extends JpaRepository<Game, String> {
}
