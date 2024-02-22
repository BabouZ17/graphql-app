package com.example.games.controller;

import com.example.games.repository.GameRepository;
import com.example.games.repository.StudioRepository;
import com.example.games.dto.GameDTO;
import com.example.games.exceptions.BadRequestException;
import com.example.games.exceptions.ResourceNotFoundException;
import com.example.games.dto.CreateGameDTO;
import com.example.games.mapper.GameMapper;
import com.example.games.model.Game;
import com.example.games.model.Studio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("games")
public class RestGameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private GameMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable String id) {
        Game game = gameRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game: " + id + " does not exist"));

        GameDTO retrievedGame = this.mapper.fromGametoGameDTO(game);
        return new ResponseEntity<>(retrievedGame, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> games = gameRepository.findAll().stream().map((game) -> this.mapper.fromGametoGameDTO(game))
                .collect(Collectors.toList());
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GameDTO> saveGame(@RequestBody @Valid CreateGameDTO gameDTO) {
        Studio studio = studioRepository
                .findById(gameDTO.getStudio())
                .orElseThrow(() -> new BadRequestException(
                        "Could not save the game, studio: " + gameDTO.getStudio() + " does not exist"));

        Game game = this.mapper.fromCreateGameDTOtoGame(gameDTO, studio);
        Game savedGame = gameRepository.save(game);
        GameDTO newGame = this.mapper.fromGametoGameDTO(savedGame);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }
}
