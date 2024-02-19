package com.example.games.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.games.model.Studio;

public interface StudioRepository extends JpaRepository<Studio, String> {
}
