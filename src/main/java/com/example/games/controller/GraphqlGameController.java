package com.example.games.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.example.games.model.Game;
import com.example.games.model.Studio;
import com.example.games.dto.GameDTO;
import com.example.games.dto.StudioDTO;
import com.example.games.dto.PlayerDTO;
import com.example.games.mapper.GameMapper;
import com.example.games.mapper.StudioMapper;
import com.example.games.repository.GameRepository;
import com.example.games.repository.StudioRepository;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GraphqlGameController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private StudioMapper studioMapper;

    @Autowired
    private GameMapper gameMapper;

    @QueryMapping
    public GameDTO gameById(@Argument String id) {
        Optional<Game> game = gameRepository.findById(id);
        if (!game.isPresent()) {
            throw new IllegalStateException(String.format("Game with %s does not exist", id.toString()));
        }
        return this.gameMapper.fromGametoGameDTO(game.get());
    }

    @QueryMapping
    public Iterable<GameDTO> games() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map((game) -> this.gameMapper.fromGametoGameDTO(game)).collect(Collectors.toList());
    }

    @QueryMapping
    public Iterable<StudioDTO> studios() {
        List<Studio> studios = studioRepository.findAll();
        return studios.stream().map((studio) -> this.studioMapper.fromStudiotoStudioDTO(studio))
                .collect(Collectors.toList());
    }

    @SchemaMapping
    public Iterable<PlayerDTO> players(GameDTO gameDTO) {
        // Not persisted into DB
        return PlayerDTO.players();
    }
}
