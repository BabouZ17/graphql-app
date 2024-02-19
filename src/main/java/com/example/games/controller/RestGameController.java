package com.example.games.controller;

import com.example.games.repository.GameRepository;
import com.example.games.repository.StudioRepository;
import com.example.games.dto.GameDTO;
import com.example.games.dto.CreateGameDTO;
import com.example.games.exception.InternalServerException;
import com.example.games.exception.ResourceNotFoundException;
import com.example.games.mapper.GameMapper;
import com.example.games.model.Game;
import com.example.games.model.Studio;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
public class RestGameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private GameMapper mapper;

    @GetMapping("/games/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable String id) {
        Optional<Game> game = gameRepository.findById(id);
        if (!game.isPresent()) {
            throw new ResourceNotFoundException(String.format("Game with %s does not exist", id.toString()));
        }
        GameDTO retrievedGame = GameMapper.fromGametoGameDTO(game.get());
        return new ResponseEntity<>(retrievedGame, HttpStatus.OK);
    }

    @GetMapping("/games")
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> games = gameRepository.findAll().stream().map((game) -> GameMapper.fromGametoGameDTO(game))
                .collect(Collectors.toList());
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PostMapping("/games")
    public ResponseEntity<GameDTO> saveGame(@Valid @RequestBody CreateGameDTO gameDTO) {
        Optional<Studio> studio = studioRepository.findById(gameDTO.getStudio());
        if (!studio.isPresent()) {
            throw new InternalServerException("Could not save the game");
        }

        Game game = GameMapper.fromCreateGameDTOtoGame(gameDTO, studio.get());
        Game savedGame = gameRepository.save(game);
        GameDTO newGame = GameMapper.fromGametoGameDTO(savedGame);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }
}
